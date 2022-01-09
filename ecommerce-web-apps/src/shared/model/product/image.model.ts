export interface IImage {
    id?: string;
    fileName?: string;
    fileDownloadUri?: string;
    fileType?: string;
    size?: number;
}

export const defaultValue: Readonly<IImage> = {};
