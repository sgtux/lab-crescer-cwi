import { useEffect, useState } from 'react'

import { adminService } from '../../services'

import { TextInput, SaveButton, ResetButton } from '../../components'
import { Container, FieldName } from './styles'

export function SecurityConfig() {

    const [cookieHttpOnly, setCookieHttpOnly] = useState(false)
    const [cookieSecure, setCookieSecure] = useState(false)
    const [cookieDomain, setCookieDomain] = useState('')
    const [cookieMinutes, setCookieMinutes] = useState(false)

    function refresh() {
        adminService.getSecurityConfig()
            .then(res => {
                setCookieHttpOnly(res.cookieHttpOnly)
                setCookieSecure(res.cookieSecure)
                setCookieDomain(res.cookieDomain || '')
                setCookieMinutes(res.cookieMinutes)
            })
            .catch(err => console.log(err))
    }

    useEffect(() => refresh(), [])

    function save() {
        adminService.updateSecurityConfig({
            cookieHttpOnly,
            cookieSecure,
            cookieDomain,
            cookieMinutes: Number(cookieMinutes || 0)
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
                <FieldName>Minutos:</FieldName>
                <TextInput style={{ width: 200 }} value={cookieMinutes} onChange={e => setCookieMinutes(e.target.value)} />
            </div>
            <br /><br />
            <SaveButton onClick={() => save()}>Salvar</SaveButton>
            <ResetButton onClick={() => reset()}>Restaurar</ResetButton>
        </Container>
    )
}