import axios from 'axios'

// import { storageService } from './storage.service'

// const getData = () => {
//     return axios.get('/usuario', { headers: { authorization: `bearer ${storageService.getToken()}` } }).then(p => p.data)
// }

const getUserData = () => {
    return axios.get('/usuario').then(p => p.data)
}

const login = (email, senha) => axios.post('/token', { email, senha }).then(p => p.data)

const logout = () => axios.get('/logout')

export const accountService = {
    login,
    getUserData,
    logout
}