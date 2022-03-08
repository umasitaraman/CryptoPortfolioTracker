# bd-team-project-crypto-wizards
bd-team-project-crypto-wizards created by GitHub Classroom

Crypto assets are digital tokens secured through a decentralized computer network. Owning several crypto assets can be difficult to track and view overall value and performance.

The crypto portfolio tracking service provides a custom view of the client's portfolio to meet their needs. 
It is designed to connect with a 3rd party price quoting service, displaying realtime data converted to USD to see an accurate asset value. 
This will keep track of all assets in a single location with historical data of transactions to view growth and regression.

We use API Gateway and Lambda to create seven endpoints (RegisterActivity,LoginActivity, VerifyActivity, CreatePortfolioActivity, GetPortfolioActivity, UpdatePortfolioActivity, GetTransactionActivity) that will handle the creation, update, and retrieval of portfolio to satisfy our requirements.

We store the assets available for the portfolio in a table in DynamoDB. The portfolios themselves will also be stored in DynamoDB.

CryptoPortfolioTracker also provides a web interface for users to manage their portfolios. A main page providing a list view will let them create new portfolios and link off to pages to update and view assets.

P.S : This was a team project and my contribution to the project was implementing the backend code for VerifyActivity, CreatePortfolioActivity, GetPortfolioActivity, UpdatePortfolioActivity, GetTransactionActivity end points
