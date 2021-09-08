import { Seller } from "./seller";

export interface Product{
  id: number;
  name: string;
  count: number;
  price: number;
  imgUrl: string;
  description: string;
  rating: number;
  seller: Seller;
  city: string;
  region: string;
}
