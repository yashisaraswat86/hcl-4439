export const isEligible = (attendance) => {
  if(attendance < 0 || attendance > 100) {
    throw new Error("Invalid attendance value. Please enter a value between 0 and 100.");
  }
  return attendance >= 75;
};