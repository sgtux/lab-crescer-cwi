import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'
import { userChanged } from '../../store/actions'

import { postService } from '../../services'
import {
    PostListContainer,
    PostListFooter,
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

    const dispatch = useDispatch()

    useEffect(() => {
        refresh()
    }, [filter])

    function refresh() {
        setShowNewPost(false)
        postService.getAll(filter)
            .then(res => setPosts(res))
            .catch(err => {
                if ((err.response || {}).status === 401)
                    dispatch(userChanged(null))
                console.log(err.response)
            })
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
                        <img src={p.foto} alt="" />
                    </PostCardBody>
                    <Line />
                    <PostFooter>
                        Comentários:

                        {!!p.comentarios.length && p.comentarios.map(x =>
                            <CommentBox key={x.id}>
                                <PostProfileImage alt="" src={x.usuario.foto} />
                                <div style={{ marginLeft: 10 }}>
                                    <div>
                                        <span style={{ fontSize: 20, fontWeight: 'bold' }}>{x.usuario.nomeCompleto}</span>
                                        <span style={{ fontSize: 12 }}>{x.criadoEmFormatado}</span>
                                        {x.owner &&
                                            <BtnRemoveComment></BtnRemoveComment>
                                        }
                                    </div>
                                    <span>{x.texto}</span>
                                </div>
                            </CommentBox>)
                        }

                        <NewCommentBox>
                            <input placeholder="Write a comment..." />
                            <BtnSendComment>SEND</BtnSendComment>
                        </NewCommentBox>
                    </PostFooter>
                </PostCard>)
            }
            <PostListFooter>Terms · Privacy · Program Policies </PostListFooter>
            <NewPostModal onSuccess={() => refresh()} onClose={() => setShowNewPost(false)} />
            <NewPostButtonBox>
                <NewPostButton>Novo Post</NewPostButton>
            </NewPostButtonBox>
        </PostListContainer >
    )
}