-- ####################################################################
-- ###                  STATION DATA (20 MAJOR STATIONS)            ###
-- ####################################################################
-- Using ON CONFLICT(id) DO NOTHING prevents errors if the script is run again.
INSERT INTO stations (id, station_name, station_code) VALUES
(101, 'New Delhi', 'NDLS'),
(102, 'Mumbai CSMT', 'CSMT'),
(103, 'Chennai Central', 'MAS'),
(104, 'Kolkata Howrah', 'HWH'),
(105, 'Bengaluru City', 'SBC'),
(106, 'Hyderabad Deccan', 'HYB'),
(107, 'Jaipur Junction', 'JP'),
(108, 'Ahmedabad Junction', 'ADI'),
(109, 'Lucknow Charbagh', 'LKO'),
(110, 'Varanasi Junction', 'BSB'),
(111, 'Pune Junction', 'PUNE'),
(112, 'Nagpur Junction', 'NGP'),
(113, 'Bhopal Junction', 'BPL'),
(114, 'Patna Junction', 'PNBE'),
(115, 'Guwahati', 'GHY'),
(116, 'Bhubaneswar', 'BBS'),
(117, 'Ernakulam Junction', 'ERS'),
(118, 'Kanyakumari', 'CAPE'),
(119, 'Jammu Tawi', 'JAT'),
(120, 'Amritsar Junction', 'ASR')
ON CONFLICT (id) DO NOTHING;


-- ####################################################################
-- ###                    TRAIN DATA (15 TRAINS)                    ###
-- ####################################################################
INSERT INTO trains (id, train_name, train_number, total_seats) VALUES
(1001, 'Mumbai Rajdhani', '12952', 750),
(1002, 'Grand Trunk Express', '12616', 800),
(1003, 'Coromandel Express', '12841', 820),
(1004, 'Karnataka Express', '12628', 780),
(1005, 'Himsagar Express', '16318', 700),
(1006, 'Poorva Express', '12304', 760),
(1007, 'Duronto Express (Mumbai)', '12261', 850),
(1008, 'Shatabdi Express (Bhopal)', '12002', 650),
(1009, 'Sampark Kranti (Bengaluru)', '12650', 790),
(1010, 'Kalka Mail', '12311', 720),
(1011, 'Gujarat Mail', '12901', 740),
(1012, 'Konark Express', '11019', 810),
(1013, 'Charminar Express', '12759', 830),
(1014, 'Saraighat Express', '12345', 770),
(1015, 'Island Express', '16526', 710)
ON CONFLICT (id) DO NOTHING;


-- ####################################################################
-- ###                 SCHEDULE DATA (ROUTES FOR TRAINS)            ###
-- ####################################################################

-- Train 1001 (Mumbai Rajdhani): New Delhi -> Jaipur -> Mumbai
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1001, 101, '16:55:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1001, 107, '21:30:00', '21:35:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1001, 102, '08:15:00', 3);

-- Train 1002 (Grand Trunk Express): New Delhi -> Bhopal -> Nagpur -> Chennai
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1002, 101, '16:10:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1002, 113, '01:15:00', '01:25:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1002, 112, '07:30:00', '07:35:00', 3);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1002, 103, '06:20:00', 4);

-- Train 1003 (Coromandel Express): Kolkata -> Bhubaneswar -> Chennai
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1003, 104, '15:20:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1003, 116, '21:50:00', '21:55:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1003, 103, '17:00:00', 3);

-- Train 1004 (Karnataka Express): New Delhi -> Pune -> Bengaluru
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1004, 101, '20:20:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1004, 111, '20:10:00', '20:15:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1004, 105, '12:00:00', 3);

-- Train 1005 (Himsagar Express): Jammu -> New Delhi -> Ernakulam -> Kanyakumari (Longest Route)
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1005, 119, '23:45:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1005, 101, '10:30:00', '11:00:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1005, 117, '15:00:00', '15:05:00', 3);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1005, 118, '21:30:00', 4);

-- Train 1006 (Poorva Express): New Delhi -> Patna -> Kolkata
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1006, 101, '17:40:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1006, 114, '05:00:00', '05:10:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1006, 104, '17:00:00', 3);

-- Train 1007 (Duronto Express): Kolkata -> Mumbai (Point-to-Point)
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1007, 104, '05:45:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1007, 102, '08:15:00', 2);

-- Train 1008 (Shatabdi Express): New Delhi -> Bhopal
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1008, 101, '06:00:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1008, 113, '14:05:00', 2);

-- Train 1009 (Sampark Kranti): New Delhi -> Hyderabad -> Bengaluru
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1009, 101, '06:00:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1009, 106, '05:30:00', '05:35:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1009, 105, '18:50:00', 3);

-- Train 1010 (Kalka Mail): Kolkata -> New Delhi -> Amritsar
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1010, 104, '19:40:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1010, 101, '04:30:00', '04:50:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1010, 120, '13:00:00', 3);

-- Train 1011 (Gujarat Mail): Mumbai -> Ahmedabad
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1011, 102, '22:15:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1011, 108, '06:25:00', 2);

-- Train 1012 (Konark Express): Mumbai -> Pune -> Bhubaneswar
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1012, 102, '15:10:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1012, 111, '19:00:00', '19:05:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1012, 116, '04:25:00', 3);

-- Train 1013 (Charminar Express): Chennai -> Hyderabad
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1013, 103, '17:10:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1013, 106, '06:35:00', 2);

-- Train 1014 (Saraighat Express): Kolkata -> Guwahati
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1014, 104, '15:55:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1014, 115, '09:50:00', 2);

-- Train 1015 (Island Express): Bengaluru -> Ernakulam -> Kanyakumari
INSERT INTO schedules (train_id, station_id, departure_time, stop_number) VALUES (1015, 105, '20:00:00', 1);
INSERT INTO schedules (train_id, station_id, arrival_time, departure_time, stop_number) VALUES (1015, 117, '08:35:00', '08:40:00', 2);
INSERT INTO schedules (train_id, station_id, arrival_time, stop_number) VALUES (1015, 118, '14:50:00', 3);