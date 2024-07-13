import axios from 'axios'

import {storageService} from './storage.service'

const obterTodos = filtro => axios.get(`/post?filtro=${filtro || ''}`, storageService.getAuthHeaders()).then(p => p.data)

const deletarPost = id => axios.delete(`/post/${id}`, storageService.getAuthHeaders())

const adicionarComentario = comentario => axios.post('/comentario', comentario, storageService.getAuthHeaders())

const deletarComentario = id => axios.delete(`/comentario/${id}`, storageService.getAuthHeaders())

export const postService = {
    obterTodos,
    deletarPost,
    adicionarComentario,
    deletarComentario
}