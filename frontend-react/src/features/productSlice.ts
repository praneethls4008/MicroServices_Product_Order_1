import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
}

interface productState {
  products: Product[];
}

const initialState: productState = {
  products: [],
};

const productSlice = createSlice({
  name: "product",
  initialState,
  reducers: {
    addToProducts: (state, action: PayloadAction<Product>) => {
      state.products.push(action.payload);
    },

    addAllProducts: (state, action: PayloadAction<Product[]>) => {
      state.products.concat(action.payload);
    },

    removeFromProducts: (state, action: PayloadAction<Product>) => {
      state.products = state.products.filter(
        (currProd) => currProd.id !== action.payload.id
      );
    },
  },
});

export const { addToProducts, addAllProducts, removeFromProducts } =
  productSlice.actions;
export default productSlice.reducer;
