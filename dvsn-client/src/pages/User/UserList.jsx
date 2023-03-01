import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'

import { UserBox, Container, UserImage } from './styles'
import { usuarioService } from '../../services'
import { userChanged } from '../../store/actions'
import { Footer, SearchBtn, SearchInput } from '../../components'

export function UserList() {

    const [users, setUsers] = useState([])
    const [filtro, setFiltro] = useState('')

    useEffect(() => atualizar(), [])

    const dispatch = useDispatch()

    function atualizar() {
        usuarioService.buscar(filtro)
            .then(res => setUsers(res))
            .catch(err => {
                if ((err.response || {}).status === 401)
                    dispatch(userChanged(null))
                console.error(err)
            })
    }

    return (
        <Container>
            <SearchInput onChange={e => setFiltro(e.target.value)} placeholder='Nome ...' />
            <SearchBtn onClick={() => atualizar()}>Pesquisar</SearchBtn>
            {!!users.length && users.map(p =>
                <UserBox key={p.id}>
                    <UserImage src={p.foto || '/images/profile-default.jpg'} />
                    <div style={{ margin: 10, textAlign: 'left' }}>
                        <div style={{ marginTop: 6 }}>{p.nome} {p.sobrenome}</div>
                        <div style={{ marginTop: 6 }}>Posts: {p.quantidadePosts}</div>
                        <div style={{ marginTop: 6 }}>Registrado: {p.criadoEmFormatado}</div>
                    </div>
                </UserBox>
            )}
            <Footer>Mantenha a segurança simples</Footer>
        </Container>
    )
}