import React, { useEffect, useState } from 'react'

import { FormContainer, TextInput, Button, Line, ChangeScreenButton, ErrorMessage } from './styles'

import { accountService } from '../../services'

export function Create({ onChangeMode }) {


    const [nome, setNome] = useState('')
    const [sobrenome, setSobrenome] = useState('')
    const [email, setEmail] = useState('')
    const [senha, setSenha] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => setErrorMessage(''), [nome, sobrenome, email, senha])

    async function create() {

        try {
            await accountService.create({ nome, sobrenome, email, senha })
            onChangeMode()
        } catch (err) {
            if (typeof (err.toJSON) === 'function' && err.toJSON().status === 400) {
                setErrorMessage(err.response.data.erro)
            }
            else
                setErrorMessage('Erro desconhecido.')
        }
    }

    return (
        <FormContainer>
            <TextInput placeholder='Nome' value={nome} onChange={e => setNome(e.target.value)} />
            <TextInput placeholder='Sobrenome' value={sobrenome} onChange={e => setSobrenome(e.target.value)} />
            <TextInput placeholder='Email' value={email} onChange={e => setEmail(e.target.value)} />
            <TextInput placeholder='Senha' type="password" value={senha} onChange={e => setSenha(e.target.value)} />
            <Button onClick={() => create()}>Criar Conta</Button>
            <ErrorMessage>{errorMessage}</ErrorMessage>
            <Line />
            <ChangeScreenButton onClick={() => onChangeMode()}>Entrar</ChangeScreenButton>
        </FormContainer>
    )
}