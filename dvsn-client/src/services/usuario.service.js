import axios from 'axios'

// import { storageService } from './storage.service'

// const getData = () => {
//     return axios.get('/usuario', { headers: { authorization: `bearer ${storageService.getToken()}` } }).then(p => p.data)
// }

const getUserData = () => axios.get('/usuario').then(p => p.data)

const buscar = filtro => axios.get(`/usuarios?filtro=${filtro || ''}`).then(p => p.data)

const login = (email, senha) => axios.post('/login', { email, senha }).then(p => p.data)

const create = user => axios.post('/criarConta', user)

const logout = () => axios.get('/logout')

export const usuarioService = {
    getUserData,
    buscar,
    login,
    logout,
    create
}