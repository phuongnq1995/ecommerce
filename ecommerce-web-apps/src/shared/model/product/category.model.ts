export interface ICategory {
    id?: string;
    name?: string;
    code?: string;
    description?: string;
    parentId?: string;
}

export const defaultValue: Readonly<ICategory> = {};
