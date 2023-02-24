import { useEffect, useState } from 'react'

import { OverlayContainer, Title, Line, CreatePostButton, CancelPostButton, PostContainer, PostText, ErrorMessage } from './styles'

export function NewPostModal({ onClose, onSuccess }) {

    const [file, setFile] = useState(null)
    const [text, setText] = useState('')
    const [errorMessage, setErrorMessage] = useState('')

    useEffect(() => setErrorMessage(''), [file, text])

    function create() {
        const formData = new FormData()
        formData.append('text', text)
        formData.append('image', file)
        fetch('/post', { method: 'post', body: formData })
            .then(res => {
                if (res.status === 200)
                    onSuccess()
                else
                    setErrorMessage('Não foi possível salvar o Post.')
            })
            .catch(err => console.error(err))
    }

    return (
        <OverlayContainer>
            <PostContainer>
                <Title>Criar Post</Title>
                <Line />
                <PostText placeholder='Escreva algo...' onChange={(e) => setText(e.target.value)}></PostText>
                <input type='file' style={{ marginTop: 20 }} onChange={e => setFile(e.target.files[0])} />
                {errorMessage && <ErrorMessage>{errorMessage}</ErrorMessage>}
                <Line />
                <div style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
                    <CancelPostButton onClick={() => onClose()}>Fechar</CancelPostButton>
                    <CreatePostButton disabled={!text} onClick={() => create()}>Criar</CreatePostButton>
                </div>
            </PostContainer>
        </OverlayContainer>
    )
}