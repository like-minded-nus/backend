USE like_minded;

-- Insert 10 rows of voucher types
INSERT INTO voucher_type (VOUCHER_TYPE, VOUCHER_TYPE_DESC) VALUES
(1000, '520'),
(1002, 'Valentines'),
(1003, 'VIP Discount'),
(1004, 'Black Friday'),
(1005, 'Weekend Special'),
(1006, 'Holiday Discount'),
(1007, 'Limited Time Offer'),
(1008, 'Promotional Code'),
(1009, 'Exclusive Deal');

-- Insert 20 rows of vendor
INSERT INTO vendor (VENDOR_ID, VENDOR_NAME, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, address, phone_number, website, activity_name, PASSION_ID, VENDOR_TYPE, INTENSITY_LEVEL, CONVERSATION_FRIENDLY)
VALUES
(1000, 'Foodie Delight', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Food Street', 12345678, 'http://foodiedelight.com', 'Food Tasting', 14, 'INDOOR', 'Medium', 'YES'),
(1001, 'Adventure Explorers', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Adventure Road', 98765432, 'http://adventureexplorers.com', 'Adventure Tours', 18, 'OUTDOOR', 'High', 'YES'),
(1002, 'Artistic Creations', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Art Avenue', 87654321, 'http://artisticcreations.com', 'Art Classes', 13, 'INDOOR', 'Low', 'YES'),
(1003, 'Nature Lovers Retreat', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Nature Trail', 23456789, 'http://natureloversretreat.com', 'Nature Hiking', 17, 'OUTDOOR', 'Medium', 'YES'),
(1004, 'Music Mania Studio', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Melody Lane', 34567890, 'http://musicmaniastudio.com', 'Music Lessons', 6, 'INDOOR', 'High', 'NO'),
(1005, 'Fitness Freaks Gym', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Fitness Avenue', 45678901, 'http://fitnessfreaksgym.com', 'Fitness Classes', 7, 'INDOOR', 'High', 'YES'),
(1006, 'Pet Paradise', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Pet Street', 56789012, 'http://petparadise.com', 'Pet Grooming', 19, 'INDOOR', 'Medium', 'YES'),
(1007, 'Fashionista Boutique', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Fashion Avenue', 67890123, 'http://fashionistaboutique.com', 'Fashion Retail', 16, 'INDOOR', 'Low', 'YES'),
(1008, 'Tech Innovation Hub', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Innovation Road', 78901234, 'http://techinnovationhub.com', 'Tech Workshops', 20, 'INDOOR', 'High', 'YES'),
(1009, 'Reading Retreat', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Reading Street', 89012345, 'http://readingretreat.com', 'Book Club', 5, 'INDOOR', 'Low', 'YES'),
(1010, 'Dance Dynamics Studio', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Dance Avenue', 90123456, 'http://dancedynamicsstudio.com', 'Dance Classes', 8, 'INDOOR', 'Medium', 'YES'),
(1011, 'Movie Maniacs Cinema', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Movie Street', 12345678, 'http://moviemaniacscinema.com', 'Movie Screenings', 9, 'INDOOR', 'Medium', 'YES'),
(1012, 'Writing Wonders Workshop', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Writing Avenue', 23456789, 'http://writingwondersworkshop.com', 'Writing Workshops', 11, 'INDOOR', 'Medium', 'YES'),
(1013, 'Yoga Bliss Studio', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Yoga Street', 34567890, 'http://yogablissstudio.com', 'Yoga Classes', 12, 'INDOOR', 'High', 'YES'),
(1014, 'Gaming Galaxy Arcade', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Gaming Street', 45678901, 'http://gaminggalaxyarcade.com', 'Gaming Arcade', 15, 'INDOOR', 'High', 'YES'),
(1015, 'Conversation Cafe', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Cafe Avenue', 56789012, 'http://conversationcafe.com', 'Cafe', NULL, 'INDOOR', 'Medium', 'YES'),
(1016, 'Fitness in the Park', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Park Road', 67890123, 'http://fitnessinthepark.com', 'Outdoor Fitness Classes', 7, 'OUTDOOR', 'High', 'YES'),
(1017, 'Travelers Haven Hostel', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '123 Traveler Street', 78901234, 'http://travelershavenhostel.com', 'Hostel', 2, 'INDOOR', 'Low', 'YES'),
(1018, 'Surf n Turf Restaurant', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '456 Surf Avenue', 89012345, 'http://surfnurfrestaurant.com', 'Restaurant', NULL, 'INDOOR', 'High', 'YES'),
(1019, 'Technology Titans Conference', 'DB_ADMIN', '2024-04-22 08:00:00', 'DB_ADMIN', '2024-04-22 08:00:00', '789 Technology Road', 90123456, 'http://technologytitansconference.com', 'Tech Conference', 20, 'INDOOR', 'High', 'YES');

-- Insert 20 rows of voucher
INSERT INTO voucher (VOUCHER_ID, VENDOR_ID, VOUCHER_NAME, VOUCHER_TYPE, VOUCHER_AMOUNT, voucher_end_date, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE, REDEEM_STATUS)
VALUES
(1000, 1000, 'Valentines with Foodie Delight', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1001, 1001, 'Valentines with Adventure Explorers', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1002, 1002, 'Valentines with Artistic Creations', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1003, 1003, 'Valentines with Nature Lovers Retreat', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1004, 1004, 'Valentines with Music Mania Studio', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1005, 1005, 'Valentines with Fitness Freaks Gym', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1006, 1006, 'Valentines with Pet Paradise', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1007, 1007, 'Valentines with Fashionista Boutique', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1008, 1008, 'Valentines with Tech Innovation Hub', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1009, 1009, 'Valentines with Reading Retreat', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1010, 1010, 'Valentines with Dance Dynamics Studio', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1011, 1011, 'Valentines with Movie Maniacs Cinema', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1012, 1012, 'Valentines with Writing Wonders Workshop', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1013, 1013, 'Valentines with Yoga Bliss Studio', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1014, 1014, 'Valentines with Gaming Galaxy Arcade', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1015, 1015, 'Valentines with Nature Delight Park', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1016, 1016, 'Valentines with Movie Munchies Snack Bar', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1017, 1017, 'Valentines with Adventure Zone Theme Park', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1018, 1018, 'Valentines with Surf n Turf Restaurant', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0),
(1019, 1019, 'Valentines with Technology Titans Conference', 1002, 10, '2024-05-01', 'SYSTEM', NOW(), 'SYSTEM', NOW(), 0);
