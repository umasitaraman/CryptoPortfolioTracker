# Example Project Design Doc - Amazon Music Playlist Service Design

Please use the following as a reference while completing your design. It has
been filled out with the details of the starter project you will be receiving,
based on the Unit 4 project.

## 1. Problem Statement

[Amazon Music Unlimited](https://www.amazon.com/b?ie=UTF8&node=15730321011) is a
premium music subscription service featuring tens of millions of songs available
to our millions of customers. We currently provide expert curated, pre-made
playlists, but customers have often requested the ability to create and manage
their own custom playlists.

This design document describes the Amazon Music Playlist Service, a new service that will provide the custom playlist functionality to meet
our customers' needs. It is designed to interact with the Amazon Music client
(which allows customers to stream music from their Amazon Music subscriptionDao to their
phone or computer) and will return a list of song metadata associated with the
playlist that the Amazon Music client can use to fetch the song file when
playing.

## 2. Top Questions to Resolve in Review

1. Should GET playlist and GET playlist/songs be separate endpoints? Will we
   regret not putting those together if by far the most common request for a
   playlist is getting the songs? 
2. We don't think ASIN + track number is the best unique identifier for songs
   (the same song might be on multiple albums, or there might be multiple ASINs
   for the same album, leading to weird duplicates). If we decide later on to
   change identifiers, will our current design make that too painful of a
   transition? Should we consider having our own songs table? Or have our own
   identifiers that *point to* ASINs + track numbers for now?
3. Do you know of any other teams out there who are working on related problems?
   Or might have the same concerns about how to identify a unique song?

## 3. Use Cases

U1. As a customer, I want to create a new, empty playlist with a given name and
a list of tags.

U2. As a customer, I want to retrieve my playlist with a given ID.

U3. As a customer, I want to update my playlist name.

U4. As a customer, I want to add a song to the end of my playlist.

U5. As a customer, I want to add a song to the beginning of my playlist.

U6. As a customer, I want to retrieve all songs in my playlist.

U7. As a customer, I want to retrieve all songs in my playlist in a provided
order (default order, reverse order, shuffled).

## 4. Project Scope

### 4.1. In Scope

* Creating, retrieving, and updating a playlist
* Adding to and retrieving a saved playlist's list of songs

### 4.2. Out of Scope

* Updating playlist tags
* Integration with Amazon Music Client
* When adding a song on the website, having a drop down or autocomplete with the
  different songs available.
* The ability to play music from the website
* The ability to search for existing songs either through the website or the API
* Being able to add tags individually or in some nicer way than a comma
  separated list of strings on the website
* Sharing playlists between users

## 5. Proposed Architecture Overview

This initial iteration will provide the minimum lovable product (MLP) including
creating, retrieving, and updating a playlist, as well as adding to and
retrieving a saved playlist's list of songs.

We will use API Gateway and Lambda to create five endpoints (`GetPlaylist`,
`CreatePlaylist`, `UpdatePlaylist`, `AddSongToPlaylist`, and `GetPlaylistSongs`)
that will handle the creation, update, and retrieval of playlists to satisfy our
requirements.

We will store songs available for playlists in a table in DynamoDB. Playlists
themselves will also be stored in DynamoDB. For simpler song list retrieval, we
will store the list of songs in a given playlist directly in the playlists
table.

AmazonMusicPlaylistService will also provide a web interface for users to manage
their playlists. A main page providing a list view of all of their playlists
will let them create new playlists and link off to pages per-playlist to update
metadata and add songs.

## 6. API

### 6.1. Public Models

```
// PlaylistModel

String id;
String name;
String customerId;
Integer songCount;
List<String> tags;
```

```
// SongModel

String asin;
String album;
Integer trackNumber;
String title;
```

### 6.2. Get Playlist Endpoint

* Accepts `GET` requests to `/playlists/:id`
* Accepts a playlist ID and returns the corresponding PlaylistModel.
    * If the given playlist ID is not found, will throw a
      `PlaylistNotFoundException`

### 6.3. Create Playlist Endpoint

* Accepts `POST` requests to `/playlists`
* Accepts data to create a new playlist with a provided name, a given customer
  ID, and an optional list of tags. Returns the new playlist, including a unique
  playlist ID assigned by the Music Playlist Service.
* For security concerns, we will validate the provided playlist name does not
  contain any invalid characters: `" ' \`
    * If the playlist name contains any of the invalid characters, will throw an
      `InvalidAttributeValueException`.

### 6.4. Update Playlist Endpoint

* Accepts `PUT` requests to `/playlists/:id`
* Accepts data to update a playlist including a playlist ID, an updated playlist
  name, and the customer ID associated with the playlist. Returns the updated
  playlist.
    * If the playlist ID is not found, will throw a `PlaylistNotFoundException`
* For security concerns, we will validate the provided playlist name does not
  contain invalid characters: `" ' \`
    * If the playlist name contains invalid characters, will throw an
      `InvalidAttributeValueException`

![Client sends submit playlist update form to Website Playlist page. Website
playlist page sends an update request to UpdatePlaylistActivity.
UpdatePlaylistActivity saves updates to the playlists
database.](images/example_design_document/UpdatePlaylistSD.png)

### 6.5. Add Song To Playlist Endpoint

* Accepts `POST` requests to `/playlists/:id/songs`
* Accepts a playlist ID and a song to be added. The song is specified by the
  album's ASIN and song track number
    * If the playlist is not found, will throw a `PlaylistNotFoundException`
    * If the given album ASIN doesn't exist, or if the given track number does
      not exist for the album ASIN, will throw an `AlbumTrackNotFoundException`
* By default, will insert the new song to the end of the playlist
    * If the optional `queueNext` parameter is provided and is `true`, this API
      will insert the new song to the front of the playlist so that it will be
      the next song played

![Client submits the add song form to the Website Add Song page. The website
add song page sends an add song request to the AddSongToPlaylistActivity. The
AddSongToPlaylistActivity save the updated playlist song list in the playlists
database.](images/example_design_document/AddSongSD.png)

### 6.6. Get Playlist Songs Endpoint

* Accepts `GET` requests to `/playlists/:id/songs`
* Retrieves all songs of a playlist with the given playlist ID
    * Returns the song list in default playlist order
    * If the optional `order` parameter is provided, this API will return the
      song list in order, reverse order, or shuffled order, based on the value
      of `order`
        * DEFAULT - same as default behavior, returns songs in playlist order
        * REVERSED - returns playlist songs in reversed order
        * SHUFFLED - returns playlist songs in a randomized order
* If the playlist ID is found, but contains no songs, the songs list will be
  empty
* If the playlist ID is not found, will throw a `PlaylistNotFoundException`

![The client visits the playlist page of the Website Playlist. The Website
playlist page sends a get song request to the GetPlaylistSongsActivity. The
GetPlaylistSongsActivity calls the playlists database to load the playlist. The
playlists database returns the playlist item to the GetPlaylistSongsActivity.
The GetPlaylistSongsActivity returns a List<SongModel> to the Website Playlist
page. The Website playlist page presents a list of songs to the
client.](images/example_design_document/GetPlaylistSD.png)

## 7. Tables

### 7.1. `playlists`

```
id // partition key, string
name // string
customerId // string
songCount // number
tags // stringSet
songList // list
```

### 7.2. `album_tracks`

```
asin // partition key, string
track_number // sort key, number
album_name // string
song_title // string
```

## 8. Pages

![Clicking on a playlist name on the home page opens the view playlist page.
Clicking the plus icon on the homepage opens the create playlist page. Clicking
the Create button on the create playlist page opens the view playlist page.
Clicking the plus icon on the view playlist page opens the add song page.
Clicking add on the Add Song page opens the view playlist
page.](images/example_design_document/OverallWorkflow.png)

![The homepage has a header that reads "Amazon Playlists." It also displays the
user's alias. Each page has this header. Each playlist the user has is shown as
a box with the playlist name and any tags it has. There is also a plus
icon.](images/example_design_document/Homepage.png)

![The create playlist page says "Create Playlist" with a field for name and
tags. There is a "Create"
button.](images/example_design_document/CreatePlaylist.png)

![The view playlist page has the name of the playlist and any tags associated
with it. Each song in the playlist is listed in order. There is a plus button
to add a new song to the
playlist.](images/example_design_document/ViewPlaylist.png)

![The add song page has a form titled "Add Song." It displays the playlist name
followed by fields for the asin and track number. THere is a check box for
queue next. Finally, there is an "Add" button to submit the
form.](images/example_design_document/AddSong.png)
