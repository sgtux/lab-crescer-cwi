import { useEffect, useState, useCallback } from 'react'
import { useDispatch } from 'react-redux'

import { adminService } from '../../services'

import { TextInput, SaveButton, ResetButton, ErrorMessage, SuccessMessage } from '../../components'
import { Container, FieldName, GroupField, FieldBox, Hr } from './styles'

import { userChanged, securityConfigChanged } from '../../store/actions'

export function SecurityConfig() {

    const [errorMessage, setErrorMessage] = useState('')
    const [successMessage, setSuccessMessage] = useState('')
    const [xssPreventionEnabled, setXssPreventionEnabled] = useState(false)
    const [xssStoredPreventionEnabled, setXssStoredPreventionEnabled] = useState(false)
    const [sqlInjectionPreventionEnabled, setSqlInjectionPreventionEnabled] = useState(false)
    const [csrfTokenEnabled, setCsrfTokenEnabled] = useState(false)
    const [cookieHttpOnly, setCookieHttpOnly] = useState(false)
    const [cookieSecure, setCookieSecure] = useState(false)
    const [cookieDomain, setCookieDomain] = useState('')
    const [cookieSameSite, setCookieSameSite] = useState('Empty')
    const [sessionMinutes, setSessionMinutes] = useState(false)
    const [tipoAutenticacao, setTipoAutenticacao] = useState('CookieBase64')
    const [xFrameOptionsHeader, setXFrameOptionsHeader] = useState('Empty')
    const [urlIframe, setUrlIframe] = useState(window.location.href)
    const [contentSecurityPolicy, setContentSecurityPolicy] = useState('')
    const [cors, setCors] = useState('')

    const dispatch = useDispatch()

    const refresh = useCallback(async () => {
        try {
            const res = await adminService.getSecurityConfig()
            setXssPreventionEnabled(res.xssPreventionEnabled)
            setXssStoredPreventionEnabled(res.xssStoredPreventionEnabled)
            setSqlInjectionPreventionEnabled(res.sqlInjectionPreventionEnabled)
            setCsrfTokenEnabled(res.csrfTokenEnabled)
            setCookieHttpOnly(res.cookieHttpOnly)
            setCookieSecure(res.cookieSecure)
            setCookieDomain(res.cookieDomain || '')
            setCookieSameSite(res.cookieSameSite || '')
            setSessionMinutes(res.sessionMinutes)
            setTipoAutenticacao(res.tipoAutenticacao)
            setXFrameOptionsHeader(res.xFrameOptionsHeader)
            setContentSecurityPolicy(res.contentSecurityPolicy || '')
            setCors(res.cors || '')
            dispatch(securityConfigChanged(res))

            setErrorMessage('')
            setSuccessMessage('')
        } catch (err) {
            if (err.toJSON().status === 401) {
                dispatch(userChanged(null))
            }
        }
    }, [dispatch])

    useEffect(() => refresh(), [refresh])

    async function save() {

        try {
            const res = await adminService.updateSecurityConfig({
                xssPreventionEnabled,
                xssStoredPreventionEnabled,
                sqlInjectionPreventionEnabled,
                csrfTokenEnabled,
                cookieHttpOnly,
                cookieSecure,
                cookieDomain,
                cookieSameSite,
                sessionMinutes: Number(sessionMinutes || 0),
                tipoAutenticacao,
                xFrameOptionsHeader,
                contentSecurityPolicy,
                cors
            })
            if (res.status === 200) {
                setSuccessMessage('Salvo com sucesso.')
                setTimeout(() => refresh(), 1000)
            } else if (res.status === 400) {
                const json = await res.json()
                setErrorMessage(json.erro)
                console.log(json.erro)
            } else {
                setErrorMessage('Não foi possível salvar.')
            }
        } catch (err) {
            if (typeof (err.toJSON) === 'function' && err.toJSON().status === 400) {
                setErrorMessage(err.response.data.erro)
            }
        }
    }

    function reset() {
        adminService.resetSecurityConfig()
            .then(() => refresh())
            .catch(err => console.log(err))
    }

    return (
        <Container>
            <GroupField>
                <legend>Críticas</legend>
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
                <FieldBox>
                    <FieldName>Utilizar Csrf Token:</FieldName>
                    <input type="checkbox" checked={csrfTokenEnabled} onChange={e => setCsrfTokenEnabled(e.target.checked)} />
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
                <FieldBox>
                    <FieldName>Same Site:</FieldName>
                    <select value={cookieSameSite} onChange={e => setCookieSameSite(e.target.value)}>
                        <option value="Empty">Vazio</option>
                        <option value="None">None</option>
                        <option value="Lax">Lax</option>
                        <option value="Strict">Strict</option>
                    </select>
                </FieldBox>
                {cookieSameSite !== 'Empty' && !cookieSecure && <ErrorMessage>Requer o atributo Secure</ErrorMessage>}
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
                    <FieldName>Cross Origin Resource Sharing (CORS):</FieldName>
                    <TextInput style={{ width: 500, marginTop: 6 }} value={cors} onChange={e => setCors(e.target.value)} />
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
                <br />
            </GroupField>
            {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
            {successMessage && <SuccessMessage>{successMessage}</SuccessMessage>}
            <ResetButton onClick={() => reset()}>Restaurar</ResetButton>
            <SaveButton onClick={() => save()}>Salvar</SaveButton>
            <GroupField>
                <legend>IFrame</legend>
                <FieldBox>
                    <FieldName>Url Iframe:</FieldName>
                    <TextInput style={{ width: 300 }} value={urlIframe} onChange={e => setUrlIframe(e.target.value)} />
                </FieldBox>
                <iframe src={urlIframe} title='TesteIframeOptions' style={{ width: 600, height: 400, marginTop: 30 }}></iframe>
            </GroupField>
        </Container>
    )
}