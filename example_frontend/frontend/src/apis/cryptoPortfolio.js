import axios from "axios";

// cryptoProjectUser
const instance = axios.create({
    baseURL: 'https://r6z0a5xu3f.execute-api.us-east-2.amazonaws.com/prod'
});
export default instance;

// // Unit 5 Team Project
// const instance = axios.create({
//     baseURL: 'https://ccixqpmq4c.execute-api.us-east-2.amazonaws.com/prod'
// });
// export default instance;

