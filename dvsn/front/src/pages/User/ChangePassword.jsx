import { useEffect, useState } from 'react'

import { TextInput, ErrorMessage, CustomButton, SuccessMessage } from '../../components'
import { Container } from './styles'

import { usuarioService, adminService } from '../../services'

export function ChangePassword() {

    const [password, setPassword] = useState('')
    const [confirm, setConfirm] = useState('')
    const [errorMessage, setErrorMessage] = useState('')
    const [successMessage, setSuccessMessage] = useState('')
    const [csrfToken, setCsrfToken] = useState('')

    useEffect(() => {
        adminService.getSecurityConfig()
            .then(res => {
                if (res.csrfTokenEnabled)
                    usuarioService.getCsrfToken()
                        .then(() => {
                            document.cookie.split(';').forEach(p => {
                                let [k, v] = p.split('=')
                                if (k === '_csrf')
                                    setCsrfToken(v)
                            })
                        })
            })
    }, [])

    useEffect(() => {
        setErrorMessage('')
        setSuccessMessage('')
    }, [password, confirm])

    async function save() {
        const formData = new FormData()
        formData.append('senha', password)
        formData.append('confirmacao', confirm)
        if (csrfToken)
            formData.append('token', csrfToken)
        try {
            const res = await fetch('/usuario/alterar-senha', { method: 'post', body: formData })
            console.log(res)
            if (res.status === 200) {
                setSuccessMessage('Senha alterada com sucesso.')
            } else if (res.status === 400) {
                console.log('passou aqui')
                const json = await res.json()
                setErrorMessage(json.erro)
                console.log(json.erro)
            } else {
                setErrorMessage('Não foi possível alterar a senha.')
            }
        } catch (err) {
            console.log('ocorreu erro')
            console.log(err)
            if (typeof (err.toJSON) === 'function' && err.toJSON().status === 400) {
                setErrorMessage(err.response.data.erro)
            }
        }
    }

    return (
        <Container>
            <TextInput style={{ width: 200 }} value={password} placeholder="Senha" onChange={e => setPassword(e.target.value)} />
            <TextInput style={{ width: 200 }} value={confirm} placeholder="Confirmação" onChange={e => setConfirm(e.target.value)} />
            <br /><br />
            <CustomButton onClick={() => save()}>ALTERAR</CustomButton>
            {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
            {successMessage && <SuccessMessage>{successMessage}</SuccessMessage>}
        </Container>
    )
}