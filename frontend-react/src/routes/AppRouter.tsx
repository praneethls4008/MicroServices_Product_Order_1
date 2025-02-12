import { Routes, Route, BrowserRouter } from "react-router"
import ProductsPage from "../pages/ProductsPage.tsx"
import CartPage from "../pages/CartPage.tsx"
const AppRoute = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<ProductsPage />}/>
                <Route path='/cart' element={<CartPage />}/>
                <Route path='/product/:id' element={<CartPage />}/>
                <Route path='/checkout' element={<CartPage/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default AppRoute;