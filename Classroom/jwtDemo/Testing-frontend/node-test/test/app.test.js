import { app } from '../app.js';
import { test, describe } from 'node:test';
import assert from 'node:assert';

describe("Greeting functions", () => {

    test("greet returns the correct greeting", () => {
        const  expected = "Hello, World!";
        const actual =  app.greet("World");
        assert.strictEqual(actual, expected);
    });

    test("greetInHindi returns the correct greeting in Hindi", () => {
        const expected = "नमस्ते, World!";
        const actual = app.greetInHindi("World");
        assert.strictEqual(actual, expected);
    });
});