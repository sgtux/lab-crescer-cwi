import React, { useState, useEffect } from 'react'
import { useDispatch } from 'react-redux'

import { FormContainer, TextInput, Button, ChangeScreenButton, ErrorMessage, Line } from './styles'

import { userChanged } from '../../store/actions'

import { usuarioService } from '../../services'

export function Login({ onChangeMode }) {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [loading, setLoading] = useState(false)

    const dispatch = useDispatch()

    useEffect(() => setErrorMessage(''), [email, password])

    async function login(e) {
        if (e) {
            e.preventDefault()
        }

        if (loading)
            return

        setLoading(true)

        try {
            const data = await usuarioService.login(email, password)
            dispatch(userChanged(data))
        } catch (err) {
            setLoading(false)
            if (typeof (err.toJSON) === 'function' && err.toJSON().status === 401)
                setErrorMessage('Email ou senha inválido.')
            else
                setErrorMessage('Erro desconhecido.')
        }
    }

    return (
        <FormContainer>
            <form onSubmit={e => login(e)}>
                <TextInput placeholder='Email' value={email} onChange={e => setEmail(e.target.value)} />
                <TextInput placeholder='Senha' type="password" value={password} onChange={e => setPassword(e.target.value)} />
                <Button type='submit' onClick={() => login()}>Entrar</Button>
                <ErrorMessage>{errorMessage}</ErrorMessage>
            </form>
            <Line />
            <ChangeScreenButton onClick={() => onChangeMode()}>Criar Conta</ChangeScreenButton>
        </FormContainer>
    )
}