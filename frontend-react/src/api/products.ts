import api from "./axios";

interface Product{
    id: string,
    name: string
    description: string
    price: number
};

export const fetchProducts = async():Promise<Product[]> => {
    const response = await api.get('api/product');
    return response.data;
}
