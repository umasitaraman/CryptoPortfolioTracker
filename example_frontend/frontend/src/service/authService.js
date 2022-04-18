module.exports = {
    getUsername: function() {
        const user = sessionStorage.getItem('user');
        if (user === 'undefined' || !user) {
            return null;
        } else {
            return JSON.parse(user).username;
        }
    },

    getToken: function () {
        return sessionStorage.getItem('token');
    },

    isNewUser: function () {
        const user = sessionStorage.getItem('user');
        if (user === 'undefined' || !user) {
            return null;
        } else {
            return JSON.parse(user).newUser;
        }
    },

    setNewUser: function(newUser) {
        const user = sessionStorage.getItem('user');
        if (user === 'undefined' || !user) {
            return null;
        } else {
            const updatedUser = JSON.parse(user);
            updatedUser.newUser = newUser;
            sessionStorage.setItem('user', JSON.stringify(updatedUser));
        }
    },

    setUserSession: function (username, token, newUser) {
        
        const user = {
            username: username,
            newUser: newUser
        };
        sessionStorage.setItem('user', JSON.stringify(user));
        sessionStorage.setItem('token', token);
    },

    resetUserSession: function () {
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('newUser');
    }
}