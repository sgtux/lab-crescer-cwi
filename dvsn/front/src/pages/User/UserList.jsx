import { useEffect, useState, useCallback } from 'react'
import { useDispatch } from 'react-redux'

import { UserBox, Container, UserImage } from './styles'
import { usuarioService } from '../../services'
import { userChanged } from '../../store/actions'
import { SearchBtn, SearchInput } from '../../components'

export function UserList() {

    const [users, setUsers] = useState([])
    const [filtro, setFiltro] = useState('')

    const dispatch = useDispatch()

    const atualizar = useCallback(async () => {
        try {
            const res = await usuarioService.buscar(filtro)
            setUsers(res)
        } catch (err) {
            console.error(err)
            if ((err.response || {}).status === 401)
                dispatch(userChanged(null))
        }
    }, [dispatch, filtro])

    useEffect(() => atualizar(), [atualizar])

    return (
        <Container>
            <SearchInput onChange={e => setFiltro(e.target.value)} placeholder='Nome ...' />
            <SearchBtn onClick={() => atualizar()}>Pesquisar</SearchBtn>
            {!!users.length && users.map(p =>
                <UserBox key={p.id}>
                    <UserImage src={p.foto} />
                    <div style={{ margin: 10, textAlign: 'left' }}>
                        <div style={{ marginTop: 6 }}>{p.nome} {p.sobrenome}</div>
                        <div style={{ marginTop: 6 }}>Posts: {p.quantidadePosts}</div>
                        <div style={{ marginTop: 6 }}>Registrado: {p.criadoEmFormatado}</div>
                    </div>
                </UserBox>
            )}
        </Container>
    )
}