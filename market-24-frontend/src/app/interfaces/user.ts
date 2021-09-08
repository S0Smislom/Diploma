import { Product } from './product';

export interface User{
  id: number;
  username: string;
  password: string;
  name: string;
  surname: string;
  phone: string;
  email: string;
  roles: {'id': number, 'name': string}[];
  products: Product[],
}
