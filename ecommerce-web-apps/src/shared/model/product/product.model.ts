import { ICategory } from "./category.model";
import { IImage } from './image.model';

export interface IProduct {
    id?: number;
    name?: string;
    code?: string;
    description?: string;
    price?: number;
    category?: ICategory | null;
    images?: IImage[] | null;
}

export const defaultValue: Readonly<IProduct> = {};
