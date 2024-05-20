export interface Certification{
    id?:number,
    approved: boolean,
    filledBy: string,
    clickX: number[],
    clickY: number[],
    clickDrag: number[]
}
