export class NumbersUtil{
    public static subtractFromStringNumber(input: string,number:number): string {
        const num = Number(input);
        const result = num - number;
        const output = result.toString();
        return output;
    }
}
