import { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux'

import { adminService } from '../../services'

import { TextInput, SaveButton, ResetButton } from '../../components'
import { Container, FieldName } from './styles'

import { userChanged } from '../../store/actions'

export function SecurityConfig() {

    const [cookieHttpOnly, setCookieHttpOnly] = useState(false)
    const [cookieSecure, setCookieSecure] = useState(false)
    const [cookieDomain, setCookieDomain] = useState('')
    const [sessionMinutes, setSessionMinutes] = useState(false)
    const [tipoAutenticacao, setTipoAutenticacao] = useState('CookieBase64')

    const dispatch = useDispatch()

    function refresh() {
        adminService.getSecurityConfig()
            .then(res => {
                setCookieHttpOnly(res.cookieHttpOnly)
                setCookieSecure(res.cookieSecure)
                setCookieDomain(res.cookieDomain || '')
                setSessionMinutes(res.sessionMinutes)
                setTipoAutenticacao(res.tipoAutenticacao)
            })
            .catch(err => {
                console.log(err)
                if (err.toJSON().status === 401) {
                    dispatch(userChanged(null))
                }
            })
    }

    useEffect(() => refresh(), [])

    function save() {
        adminService.updateSecurityConfig({
            cookieHttpOnly,
            cookieSecure,
            cookieDomain,
            sessionMinutes: Number(sessionMinutes || 0),
            tipoAutenticacao
        })
            .then(() => {
                refresh()
            })
            .catch(err => console.log(err))
    }

    function reset() {
        adminService.resetSecurityConfig()
            .then(() => {
                refresh()
            })
            .catch(err => console.log(err))
    }

    return (
        <Container>
            <div>
                <FieldName>Http Only:</FieldName>
                <input type="checkbox" checked={cookieHttpOnly} onChange={e => setCookieHttpOnly(e.target.checked)} />
            </div>
            <br />
            <div>
                <FieldName>Secure:</FieldName>
                <input type="checkbox" checked={cookieSecure} onChange={e => setCookieSecure(e.target.checked)} />
            </div>
            <br />
            <div>
                <FieldName>Domínio:</FieldName>
                <TextInput style={{ width: 200 }} value={cookieDomain} onChange={e => setCookieDomain(e.target.value)} />
            </div>
            <div>
                <FieldName>Tempo Sessão:</FieldName>
                <TextInput style={{ width: 200 }} value={sessionMinutes} onChange={e => setSessionMinutes(e.target.value)} />
            </div>
            <div>
                <FieldName>Tipo Autenticacao:</FieldName>
                <select value={tipoAutenticacao} onChange={e => setTipoAutenticacao(e.target.value)}>
                    <option value="CookieBase64">Cookie Base64</option>
                    <option value="Jwt">Jwt</option>
                    <option value="TokenOpaco">Token Opaco</option>
                </select>
            </div>
            <br /><br />
            <ResetButton onClick={() => reset()}>Restaurar</ResetButton>
            <SaveButton onClick={() => save()}>Salvar</SaveButton>
        </Container>
    )
}