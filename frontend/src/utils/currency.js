/**
 * Format a number as Indian Rupee currency.
 * e.g., 125000 → ₹1,25,000.00
 * @param {number|string|null|undefined} val
 * @returns {string}
 */
export const inrFmt = (val) => {
  const num = parseFloat(val || 0);
  return '₹' + num.toLocaleString('en-IN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
};

/**
 * Format number in Indian locale without ₹ prefix (for tooltips that add it themselves).
 */
export const inrNum = (val) => {
  const num = parseFloat(val || 0);
  return num.toLocaleString('en-IN', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
};
