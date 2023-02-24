import axios from 'axios'

const getAll = filtro => {
    return axios.get(`/post?filtro=${filtro || ''}`).then(p => p.data)
}

export const postService = {
    getAll
}