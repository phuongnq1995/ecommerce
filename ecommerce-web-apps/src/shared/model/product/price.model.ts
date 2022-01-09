export interface IPrice {
    id?: string;
    current?: boolean;
    lastModifiedDate?: Date;
    price?: number;
    note?: string
}

export const defaultValue: Readonly<IPrice> = {};
