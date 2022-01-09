import React, { ChangeEvent, useState } from 'react';
import mediaApi from '../../services/media.api';
import { Image, Stack, Form } from 'react-bootstrap'
import { IImage } from '../../shared/model/product/image.model';

interface IUploadFileProps {
    name: string;
    onChange?: Function;
}

const UploadFile: React.FC<IUploadFileProps> = ({name, onChange}) => {

    const [images, setImages] = useState<IImage[]>([]);

    const onFileChangeHandler = function (e: ChangeEvent<HTMLInputElement>) {
        const formData = new FormData();
        let files = e.target.files;

        if (files) {
            let length: number = files?.length ? files?.length : 0;
            for (var i = 0; i < length; i++) {
                let file = files.item(i);
                if (file) {
                    formData.append('files', file);
                }
            }

            upload(formData);
        }
    };

    const upload = async function(formData: FormData) {
        const response = await mediaApi.post("/media", formData);

        setImages(response.data);
        console.log("response.data", response.data);
        console.log("images", images);
        onChange(response.data);
    }

    return (
        <div className="form-group files color">
            <Form.Control type="file" className="form-control" name="file" multiple onChange={onFileChangeHandler} />
            <Stack direction="horizontal" className='mb-3'>
            {
                images.map(image => (
                    <div key={image.id}>
                        <input type="hidden" name={name} value={image.id}/>
                        <Image rounded={true} fluid={true} src={image.fileDownloadUri} height={300} width={300}></Image>
                    </div>
                ))
            }
            </Stack>
        </div>
    );
}

export default UploadFile;