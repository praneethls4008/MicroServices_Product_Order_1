import productReducer from './../features/productSlice';
import cartReducer from './../features/cartSlice';
import { configureStore } from "@reduxjs/toolkit";


export const store = configureStore({
    reducer:{
        products: productReducer,
        cart: cartReducer,
    }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;