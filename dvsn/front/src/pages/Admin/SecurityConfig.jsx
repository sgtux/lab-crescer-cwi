import { useEffect, useState, useCallback } from 'react'
import { useDispatch } from 'react-redux'

import { adminService } from '../../services'

import { TextInput, SaveButton, ResetButton } from '../../components'
import { Container, FieldName, GroupField, FieldBox, Hr } from './styles'

import { userChanged, securityConfigChanged } from '../../store/actions'

export function SecurityConfig() {

    const [xssPreventionEnabled, setXssPreventionEnabled] = useState(false)
    const [xssStoredPreventionEnabled, setXssStoredPreventionEnabled] = useState(false)
    const [sqlInjectionPreventionEnabled, setSqlInjectionPreventionEnabled] = useState(false)
    const [cookieHttpOnly, setCookieHttpOnly] = useState(false)
    const [cookieSecure, setCookieSecure] = useState(false)
    const [cookieDomain, setCookieDomain] = useState('')
    const [sessionMinutes, setSessionMinutes] = useState(false)
    const [tipoAutenticacao, setTipoAutenticacao] = useState('CookieBase64')
    const [xFrameOptionsHeader, setXFrameOptionsHeader] = useState('Empty')
    const [urlIframe, setUrlIframe] = useState(window.location.href)
    const [contentSecurityPolicy, setContentSecurityPolicy] = useState('')

    const dispatch = useDispatch()

    const refresh = useCallback(async () => {
        try {
            const res = await adminService.getSecurityConfig()
            setXssPreventionEnabled(res.xssPreventionEnabled)
            setXssStoredPreventionEnabled(res.xssStoredPreventionEnabled)
            setSqlInjectionPreventionEnabled(res.sqlInjectionPreventionEnabled)
            setCookieHttpOnly(res.cookieHttpOnly)
            setCookieSecure(res.cookieSecure)
            setCookieDomain(res.cookieDomain || '')
            setSessionMinutes(res.sessionMinutes)
            setTipoAutenticacao(res.tipoAutenticacao)
            setXFrameOptionsHeader(res.xFrameOptionsHeader)
            setContentSecurityPolicy(res.contentSecurityPolicy)
            dispatch(securityConfigChanged(res))
        } catch (err) {
            if (err.toJSON().status === 401) {
                dispatch(userChanged(null))
            }
        }
    }, [dispatch])

    useEffect(() => refresh(), [refresh])

    function save() {
        adminService.updateSecurityConfig({
            xssPreventionEnabled,
            xssStoredPreventionEnabled,
            sqlInjectionPreventionEnabled,
            cookieHttpOnly,
            cookieSecure,
            cookieDomain,
            sessionMinutes: Number(sessionMinutes || 0),
            tipoAutenticacao,
            xFrameOptionsHeader,
            contentSecurityPolicy
        })
            .then(() => refresh())
            .catch(err => console.log(err))
    }

    function reset() {
        adminService.resetSecurityConfig()
            .then(() => refresh())
            .catch(err => console.log(err))
    }

    return (
        <Container>
            <GroupField>
                <legend>Injections</legend>
                <FieldBox>
                    <FieldName>Previnir XSS:</FieldName>
                    <input type="checkbox" checked={xssPreventionEnabled} onChange={e => setXssPreventionEnabled(e.target.checked)} />
                </FieldBox>
                <FieldBox>
                    <FieldName>Previnir XSS Armazenado:</FieldName>
                    <input type="checkbox" checked={xssStoredPreventionEnabled} onChange={e => setXssStoredPreventionEnabled(e.target.checked)} />
                </FieldBox>
                <FieldBox>
                    <FieldName>Previnir SQL Injection:</FieldName>
                    <input type="checkbox" checked={sqlInjectionPreventionEnabled} onChange={e => setSqlInjectionPreventionEnabled(e.target.checked)} />
                </FieldBox>
            </GroupField>
            <GroupField>
                <legend>Cookie</legend>
                <FieldBox>
                    <FieldName>Http Only:</FieldName>
                    <input type="checkbox" checked={cookieHttpOnly} onChange={e => setCookieHttpOnly(e.target.checked)} />
                </FieldBox>
                <FieldBox>
                    <FieldName>Secure:</FieldName>
                    <input type="checkbox" checked={cookieSecure} onChange={e => setCookieSecure(e.target.checked)} />
                </FieldBox>
                <FieldBox>
                    <FieldName>Domínio:</FieldName>
                    <TextInput style={{ width: 200 }} value={cookieDomain} onChange={e => setCookieDomain(e.target.value)} />
                </FieldBox>
            </GroupField>
            <GroupField>
                <legend>Autenticação</legend>
                <FieldBox>
                    <FieldName>Tempo Sessão:</FieldName>
                    <TextInput style={{ width: 200 }} value={sessionMinutes} onChange={e => setSessionMinutes(e.target.value)} />
                </FieldBox>
                <FieldBox>
                    <FieldName>Tipo Autenticacao:</FieldName>
                    <select value={tipoAutenticacao} onChange={e => setTipoAutenticacao(e.target.value)}>
                        <option value="CookieBase64">Cookie Base64</option>
                        <option value="Jwt">Jwt</option>
                        <option value="TokenOpaco">Token Opaco</option>
                    </select>
                </FieldBox>
            </GroupField>
            <GroupField>
                <legend>HTTP Response Headers</legend>
                <FieldBox>
                    <FieldName>Content Security Policy (CSP):</FieldName>
                    <TextInput style={{ width: 500, marginTop: 6 }} value={contentSecurityPolicy} onChange={e => setContentSecurityPolicy(e.target.value)} />
                </FieldBox>
                <Hr />
                <FieldBox>
                    <FieldName>X-Frame-Options:</FieldName>
                    <select value={xFrameOptionsHeader} onChange={e => setXFrameOptionsHeader(e.target.value)}>
                        <option value="Empty">Vazio</option>
                        <option value="SameOrigin">Same Origin</option>
                        <option value="Deny">Deny</option>
                    </select>
                </FieldBox>
                <FieldBox>
                    <FieldName>Url Iframe:</FieldName>
                    <TextInput style={{ width: 300 }} value={urlIframe} onChange={e => setUrlIframe(e.target.value)} />
                </FieldBox>
                <FieldBox>
                    <iframe src={urlIframe} title='TesteIframeOptions' width={300} height={200}></iframe>
                </FieldBox>
            </GroupField>
            <br /><br />
            <ResetButton onClick={() => reset()}>Restaurar</ResetButton>
            <SaveButton onClick={() => save()}>Salvar</SaveButton>
        </Container>
    )
}