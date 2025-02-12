import MainLayout from './layouts/MainLayout'
import AppRoute from './routes/AppRouter'
import './App.css'
import { Provider } from 'react-redux'
import { store } from './store/store'

function App() {

  return (
    <Provider store={store}>
      <MainLayout>
        <AppRoute />
      </MainLayout> 
    </Provider>
  )
}

export default App
