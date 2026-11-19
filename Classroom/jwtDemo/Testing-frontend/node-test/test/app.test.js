import { app } from '../app.js';
import { test, describe } from 'node:test';
import assert from 'node:assert';

describe("order feature", () => {
    test("that it processes the order correctly", (t) => {
        const mockDependency = t.mock.fn((amount) => {
            console.log("i am mocked processPayment function");
            return { id: "123", amount: amount };
        });

        const expected = { id: "123", amount: 5000 };

        const actual = app.processOrder(
            { amount: 5000 },
            { processPayment: mockDependency }
        );

        assert.deepStrictEqual(actual, expected);
        assert.strictEqual(mockDependency.mock.calls.length, 1);
        assert.strictEqual(mockDependency.mock.calls[0].arguments[0], 5000);
    });
});

describe("Greeting functions", () => {
    test("greet returns the correct greeting", () => {
        const expected = "Hello, World!";
        const actual = app.greet("World");
        assert.strictEqual(actual, expected);
    });

    test("greetInHindi returns the correct greeting in Hindi", () => {
        const expected = "नमस्ते, World!";
        const actual = app.greetInHindi("World");
        assert.strictEqual(actual, expected);
    });
});
