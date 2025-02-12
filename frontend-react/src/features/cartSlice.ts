import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface Cart{
    id: string
    name: string
    price: number
    quantity: number
}

interface CartState{
    cart: Cart[]
}

const initialState: CartState={
    cart:[]
}

const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers:{
        addToCart:(state, actions:PayloadAction<Cart>)=>{
            state.cart.push(actions.payload)
        },
        removeFromCart:(state, actions:PayloadAction<Cart>)=>{
            state.cart = state.cart.filter(currCart => currCart.id!==actions.payload.id)
        }
    }
})

export default cartSlice.reducer
export const {addToCart, removeFromCart} = cartSlice.actions