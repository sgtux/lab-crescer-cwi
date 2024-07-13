import { useEffect, useState } from 'react'

import { adminService } from '../../services'

import { TextInput, ErrorMessage, CustomButton } from '../../components'
import { Container, FieldName } from './styles'

export function Hash() {

    const [hash, setHash] = useState('')
    const [tipo, setTipo] = useState(0)
    const [errorMessage, setErrorMessage] = useState('')
    const [resultado, setResultado] = useState('')

    useEffect(() => {
        setErrorMessage('')
        setResultado('')
    }, [hash, tipo])

    function quebrar() {

        adminService.hashRainbowTable(hash, tipo)
            .then(res => {
                if (res)
                    setResultado(res.texto)
                else
                    setErrorMessage('Não foi possível quebrar o Hash informado.')
            })
            .catch(err => console.log(err))

    }

    return (
        <Container>
            <FieldName>Hash:</FieldName><TextInput style={{ width: 500 }} onChange={e => setHash(e.target.value)} />
            <br /><br />

            <input type="radio" name="tipo" value="1" onChange={e => setTipo(Number(e.target.value))} />
            <label>MD5</label><br />
            <input type="radio" name="tipo" value="2" onChange={e => setTipo(Number(e.target.value))} />
            <label>SHA1</label><br />
            <br />
            <CustomButton onClick={() => quebrar()}>Tentar Quebrar</CustomButton>
            <br />
            {resultado && <div><span>Resultado: </span>{resultado}</div>}
            {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
        </Container>
    )
}