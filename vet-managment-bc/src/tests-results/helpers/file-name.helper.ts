import { Request } from "express";
import { v4 as uuid } from 'uuid'


export const fileNameHelper = (req: Request, file: Express.Multer.File, cb: Function) => {

    const fileExtension = file.mimetype.split('/')[1];

    const fileName = `${ uuid() }.${ fileExtension }`;
    cb(null, `${fileName}`);
  }