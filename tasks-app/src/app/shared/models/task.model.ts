import { Status } from './status.enum';

export class Task {
    id: number;
    title: string;
    description: string;
    status: Status;
    creationDate: Date;
    modificationDate: Date;
    conclusionDate: Date;
    removedDate: Date;
    changeFields: boolean
}
