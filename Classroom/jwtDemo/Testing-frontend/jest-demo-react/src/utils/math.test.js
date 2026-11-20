import {add , subtract , multiply} from "./math";

describe("Math Utility Testing", () => {
    test("should add two numbers correctly", () => {
        expect(add(10, 20)).toBe(30);
    });

    test("should subtract two numbers correctly", () => {
        expect(subtract(5, 3)).toBe(2);
    });

    test("should multiply two numbers correctly", () => {
        expect(multiply(2, 3)).toBe(6);
    });
});
