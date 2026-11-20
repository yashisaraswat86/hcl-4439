import {isEligible} from './student';

describe('isEligible', () => {
  test('returns true for attendance >= 75', () => {
    expect(isEligible(80)).toBe(true);
  });

  test('returns false for attendance < 75', () => {
    expect(isEligible(70)).toBe(false);
  });

  test('throws an error for attendance < 0', () => {
    expect(() => isEligible(-10)).toThrow("Invalid attendance value. Please enter a value between 0 and 100.");
  });

  test('throws an error for attendance > 100', () => {
    expect(() => isEligible(110)).toThrow("Invalid attendance value. Please enter a value between 0 and 100.");
  });
});