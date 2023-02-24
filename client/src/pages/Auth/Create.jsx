import React, { useState } from 'react'
import { useDispatch } from 'react-redux'

import { FormContainer, TextInput, Button, Line, ChangeScreenButton } from './styles'

import { userChanged } from '../../store/actions'

import { accountService, storageService } from '../../services'

export function Create({ onChangeMode }) {


    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    const dispatch = useDispatch()

    async function create() {

        // try {
        //     const result = await accountService.login(username, password)
        //     const { token } = result
        //     storageService.setToken(token)
        //     const data = await accountService.getData()
        //     dispatch(userChanged(data))
        // } catch (err) {
        //     if (typeof (err.toJSON) === 'function' && err.toJSON().status === 401)
        //         setErrorMessage('Usuário ou senha inválidos.')
        //     else
        //         setErrorMessage('Erro desconhecido.')
        // }
    }

    return (
        <FormContainer>
            <TextInput placeholder='Nome' value={username} onChange={e => setUsername(e.target.value)} />
            <TextInput placeholder='Sobrenome' value={username} onChange={e => setUsername(e.target.value)} />
            <TextInput placeholder='Email' value={username} onChange={e => setUsername(e.target.value)} />
            <TextInput placeholder='Senha' type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <Button>Criar Conta</Button>
            <Line />
            <ChangeScreenButton onClick={() => onChangeMode()}>Entrar</ChangeScreenButton>
        </FormContainer>
    )
}