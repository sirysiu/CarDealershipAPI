SELECT * FROM dealerships;

SELECT v.* 
FROM vehicles v
JOIN inventory i ON v.vin = i.vin
WHERE i.dealership_id = 1;

SELECT * FROM vehicles WHERE vin = 1001;

SELECT d.name, d.address, d.phone
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
WHERE i.vin = 1001;

SELECT d.name, d.address, d.phone
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.vin = v.vin
WHERE v.make = 'Ford' AND v.model = 'Mustang' AND v.color = 'Red';

SELECT sc.*
FROM sales_contract sc
JOIN vehicles v ON sc.vin = v.vin
JOIN inventory i ON v.vin = i.vin
WHERE i.dealership_id = 1
AND sc.date BETWEEN '2024-11-01' AND '2024-11-30';


