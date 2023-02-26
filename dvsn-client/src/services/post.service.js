import axios from 'axios'

const obterTodos = filtro => axios.get(`/post?filtro=${filtro || ''}`).then(p => p.data)

const adicionarComentario = comentario => axios.post('/comentario', comentario)

const deletarComentario = id => axios.delete(`/comentario/${id}`)

export const postService = {
    obterTodos,
    adicionarComentario,
    deletarComentario
}