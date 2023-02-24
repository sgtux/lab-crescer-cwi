import React from 'react'
import { Provider } from 'react-redux'
import ReactDOM from 'react-dom'
import './index.css'
import { RootComponent } from './components'
import { Store } from './store'
import { createTheme, ThemeProvider } from '@mui/material'
import { green } from '@mui/material/colors'

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
    primary: {
      main: green[500]
    },
    text: {
      primary: '#aaa',
      secondary: '#777'
    },
  },
});

ReactDOM.render(
  <React.StrictMode>
    <ThemeProvider theme={darkTheme}>
      <Provider store={Store}>
        <RootComponent />
      </Provider>
    </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
)