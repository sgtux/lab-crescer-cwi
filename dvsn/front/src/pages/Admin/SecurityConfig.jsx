import { useEffect, useState } from 'react'

import { adminService } from '../../services'

import { TextInput, ErrorMessage, CustomButton } from '../../components'
import { Container, FieldName } from './styles'

export function SecurityConfig() {

    const [cookieHttpOnly, setCookieHttpOnly] = useState(false)
    const [saveDisabled, setSaveDisabled] = useState(true)

    useEffect(() => {
        setSaveDisabled(false)
    }, cookieHttpOnly)

    function refresh() {
        adminService.getSecurityConfig()
            .then(res => {
                setCookieHttpOnly(res.cookieHttpOnly)
            })
            .catch(err => console.log(err))
    }

    useEffect(() => refresh(), [])

    function save() {
        adminService.updateSecurityConfig({ cookieHttpOnly })
            .then(res => {
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
            <br /><br />
            <CustomButton disabled={saveDisabled} onClick={() => save()}>Salvar</CustomButton>
        </Container>
    )
}