import { useEffect, useState } from 'react'
import { useSelector, useDispatch } from 'react-redux'

import { usuarioService } from '../../services'

import { TextInput, ErrorMessage } from '../../components'
import { Container } from './styles'

import { userChanged } from '../../store/actions'

export function EditProfile() {

    const [nome, setNome] = useState('')
    const [sobrenome, setSobrenome] = useState('')
    const [file, setFile] = useState(null)
    const [errorMessage, setErrorMessage] = useState('')

    const { user } = useSelector(state => state.appState)
    const dispatch = useDispatch()

    useEffect(() => setErrorMessage(''), [nome, sobrenome])

    useEffect(() => {
        setNome(user.nome || '')
        setSobrenome(user.sobrenome || '')
    }, [user])

    function save() {
        const formData = new FormData()
        formData.append('nome', nome)
        formData.append('sobrenome', sobrenome)
        formData.append('imagem', file)
        fetch(`/usuario/${user.id}`, { method: 'put', body: formData })
            .then(async res => {
                if (res.status === 200)
                    usuarioService.getUserData()
                        .then(res => dispatch(userChanged(res)))
                        .catch(err => console.log(err))
                else if (res.status === 400) {
                    const json = await res.json()
                    setErrorMessage(json.erro)
                    console.log(json.erro)
                } else {
                    setErrorMessage('Não foi possível salvar o usuário.')
                }
            })
            .catch(err => {
                if (typeof (err.toJSON) === 'function' && err.toJSON().status === 400) {
                    setErrorMessage(err.response.data.erro)
                }
                console.error(err.toJSON)
            })
    }

    return (
        <Container>
            <TextInput value={nome} placeholder="Nome" onChange={e => setNome(e.target.value)} />
            <TextInput value={sobrenome} placeholder="Sobrenome" onChange={e => setSobrenome(e.target.value)} />
            {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
            <input type='file' style={{ marginTop: 20 }} onChange={e => setFile(e.target.files[0])} />
            <br /><br />
            <button onClick={() => save()}>SALVAR</button>
        </Container>
    )
}