import axios from 'axios'

import { storageService } from './storage.service'

// const getData = () => {
//     return axios.get('/usuario', { headers: { authorization: `bearer ${storageService.getToken()}` } }).then(p => p.data)
// }

const getUserId = () => (storageService.getUser() || {}).id || 0

const getUserData = () => axios.get(`/usuario/${getUserId()}`).then(p => p.data)

const buscar = filtro => axios.get(`/usuarios?filtro=${filtro || ''}`).then(p => p.data)

const login = (email, senha) => axios.post('/auth/login', { email, senha }).then(p => p.data)

const create = user => axios.post('/auth/criarConta', user)

const logout = () => axios.get('/auth/logout')

export const usuarioService = {
    getUserData,
    buscar,
    login,
    logout,
    create
}