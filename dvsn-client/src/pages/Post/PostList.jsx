import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { userChanged } from '../../store/actions'

import { postService } from '../../services'
import { Footer } from '../../components'

import {
    PostListContainer,
    PostCard,
    PostCardHeader,
    PostUserBox,
    PostProfileImage,
    PostFooter,
    PostCardBody,
    CommentBox,
    NewCommentBox,
    BtnSendComment,
    NewPostButtonBox,
    BtnRemoveComment,
    BtnRemove,
    Line,
    PostText,
    NewPostButton
} from './styles'

import { NewPostModal } from './NewPostModal/NewPostModal'

export function PostList() {

    const [posts, setPosts] = useState([])
    const [showNewPost, setShowNewPost] = useState(false)
    const [filter, setFilter] = useState('')
    const [comentarios, setComentarios] = useState({})

    const dispatch = useDispatch()

    useEffect(() => {
        atualizar()
    }, [filter])

    function atualizar() {
        setShowNewPost(false)
        postService.obterTodos(filter)
            .then(res => setPosts(res))
            .catch(err => {
                if ((err.response || {}).status === 401)
                    dispatch(userChanged(null))
                console.log(err)
            })
    }

    function enviarComentario(postId) {
        postService.adicionarComentario({ postId, texto: comentarios[postId] })
            .then(res => {
                const keyPair = { ...comentarios }
                keyPair[postId] = ''
                setComentarios(keyPair)
                atualizar()
            }).catch(err => console.log(err))

    }

    function deletarComentario(id) {
        postService.deletarComentario(id)
            .then(res => atualizar())
            .catch(err => console.log(err))
    }

    return (
        <PostListContainer>
            {posts.length && posts.map(p =>
                <PostCard key={p.id}>
                    <PostCardHeader>
                        <PostUserBox>
                            <PostProfileImage alt="" src={p.usuario.foto || '/images/profile-default.jpg'} />
                            <div>
                                <h4>{p.usuario.nomeCompleto}</h4>
                                <span>{p.criadoEmFormatado}</span>
                            </div>
                        </PostUserBox>
                        {
                            p.owner &&
                            <div>
                                <BtnRemove>
                                    <em className="fa fa-trash"></em>
                                </BtnRemove>
                            </div>
                        }
                    </PostCardHeader>
                    <PostCardBody>
                        <PostText>{p.texto}</PostText>
                        <div>
                            <img style={{ maxWidth: '300px', maxHeight: '300px' }} src={p.foto} alt="" />
                        </div>
                    </PostCardBody>
                    <Line />
                    <PostFooter>
                        Comentários:
                        {!!p.comentarios.length && p.comentarios.map(x =>
                            <CommentBox key={x.id}>
                                <PostProfileImage alt="" src={x.usuario.foto || '/images/profile-default.jpg'} />
                                <div style={{ marginLeft: 10 }}>
                                    <div>
                                        <span style={{ fontSize: 20, fontWeight: 'bold' }}>{x.usuario.nomeCompleto}</span>
                                        <span style={{ fontSize: 12 }}>{x.criadoEmFormatado}</span>
                                        {x.owner &&
                                            <BtnRemoveComment onClick={() => deletarComentario(x.id)}>
                                                <em className="fa fa-trash"></em>
                                            </BtnRemoveComment>
                                        }
                                    </div>
                                    <span>{x.texto}</span>
                                </div>
                            </CommentBox>)
                        }
                        <NewCommentBox>
                            <input placeholder="Escreva um comentário..."
                                value={comentarios[p.id] || ''}
                                onChange={e => setComentarios({ ...comentarios, ...{ [p.id]: e.target.value, } })}
                            />
                            <BtnSendComment onClick={() => enviarComentario(p.id)}>Enviar</BtnSendComment>
                        </NewCommentBox>
                    </PostFooter>
                </PostCard>)
            }
            {showNewPost && <NewPostModal onSuccess={() => atualizar()} onClose={() => setShowNewPost(false)} />}
            <NewPostButtonBox>
                <NewPostButton onClick={() => setShowNewPost(true)}>Novo Post</NewPostButton>
            </NewPostButtonBox>
            <Footer>Mantenha a segurança simples</Footer>
        </PostListContainer >
    )
}