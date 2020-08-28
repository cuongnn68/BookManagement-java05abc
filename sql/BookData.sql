

---- TAO DB NEU CAN -------------------------------------------------------------------------
--if not exists (select * from sys.databases where name = 'book_management_db')
--create database book_management_db
--go

--use book_management_db
--go


-- XOA CAC BANG NEU TON TAI -------------------------------------------------------------------------

if exists (select * from sys.tables where name = 'Role')
drop table [Role]
go

if exists (select * from sys.tables where name = 'Contain')
drop table Contain
go

if exists (select * from sys.tables where name = 'Book')
drop table Book
go

if exists (select * from sys.tables where name = 'User')
drop table [User]
go

if exists (select * from sys.tables where name = 'BookCase')
drop table BookCase
go

-- TAO BANG -------------------------------------------------------------------------

create table BookCase(
	book_case_id		int IDENTITY(1,1) PRIMARY KEY,
	book_case_name		nvarchar(50) UNIQUE,
)
-- TODO: chuyen tu user->bookcase thanh bookcase -> user 
create table [User](
	UserID			int IDENTITY(1,1) PRIMARY KEY,
	[user_name]		nvarchar(50) NOT NULL UNIQUE,
	[password]		nvarchar(30) NOT NULL UNIQUE CHECK (Len([password]) >= 8),
	book_case_id	int references BookCase(book_case_id)
)

create table [Role](
	UserID		int REFERENCES [User](UserID),
	authority	bit,
	PRIMARY KEY(UserID)
)

create table Book(
	book_id			int IDENTITY(1,1) PRIMARY KEY,
	book_title		nvarchar(50) UNIQUE,
	author			nvarchar(50),
	brief			nvarchar(max),
	publisher		nvarchar(50),
	content			ntext,
	category		nvarchar(50)
	)

create table Contain (
	book_case_id		int REFERENCES BookCase(book_case_id),
	book_id				int REFERENCES Book(book_id),
	create_date			DATE DEFAULT GETDATE()	
	)

--INSERT DATA (version 1.1) -------------------------------------------------------------------------

DELETE FROM BookCase;
INSERT INTO BookCase VALUES 
(N'BookCase NamNT56'),	
(N'BookCase HuynhDV1'),	
(N'BookCase NinhND1'),	
(N'BookCase TuongND4'),	
(N'BookCase CuongNN5'),
(N'BookCase ChienPV5'),
(N'BookCase AnhTHT2'),
(N'BookCase RinhTT');


INSERT INTO [User]([user_name],[password],book_case_id) 
VALUES ('NamNT56',	'nguyenthanhnam'	,1),
	   ('HuynhDV1',	'doanvanhuynh'		,2),
	   ('NinhND1',	'nguyenduyninh'		,3),
	   ('TuongND4',	'nguyenduytuong'	,4),
	   ('CuongNN5',	'nguyennhucuong'	,5),
	   ('ChienPV5',	'phamvanchien'		,6),
	   ('AnhTHT2',	'tranhatuananh'		,7),
	   ('RinhTT',	'trantherinh'		,8),
	   ('admin',	'11111111'			, null);

-- 0 user, 1 admin
INSERT INTO [Role](UserID, authority)
VALUES	(1, 0),
		(2, 0),
		(3, 0),
		(4, 0),
		(5, 0),
		(6, 0),
		(7, 0),
		(8, 0),
		(9, 1);

DELETE FROM Book;

-- sách văn hóa
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES ( N'Ăn Và Uống Của Người Việt',N'Vũ Ngọc Khánh - Hoàng Khôi',N'Cái ăn... mà nhất là tiếng “ăn”của Việt Nam bao gồm nhiều tầng ý nghĩa hoàn toàn khác với cái ăn, tiếng ăn của nhân loại. Cái khác ấy có làm nên bản sắc Việt không tùy vào sự nhận xét và đánh giá của từng người, nhưng vấn đề “ăn” thì cần được nghiên cứu, tìm hiểu công phu hơn nữa.

Khi trình bày với bạn đọc cuốn sách Ăn Và Uống Của Người Việt này, các tác giả mong muốn đi từ bữa ăn trong một gia đình đến bữa ăn của cả cộng đồng, rồi việc ăn, việc uống trong lịch sử để từ đó nghĩ đến vấn đề ẩm thực của 54 dân tộc ở khắp Bắc, Trung, Nam chứ không riêng gì xứ nào.',N'NXB Văn Hóa',
		N'Ăn mặc là chuyện bình thường trong đời sống con người. Chúng ta thường chỉ quan tâm đến khi có việc bất thường xảy ra, hoặc khi nó trở thành một vấn đề chuyên môn, vấn đề kỹ thuật. Nhưng thật ra, một việc như việc ăn chẳng hạn, xét cả bề sâu lẫn chiều rộng thì quả là một hiện tượng, một đề tài văn hóa lớn. Riêng đối với dân tộc ta thì hình như chuyện “ăn ” còn được mở rộng hơn. Không phải chỉ bao gồm những gì liên quan đến sự sống, mà còn là cả (hay tất cả) những gì thuộc về phong tục, về thẩm mỹ, về ngôn ngữ, về triết học và về tâm linh',
		N'Văn Hóa - Tôn Giáo'),
	   (N'Những vĩ nhân thay đổi thế giới',N'Đỗ Châu Huyền - Hoàng Trí Đức',
	    N'Những Vĩ Nhân Thay Đổi Thế Giới sẽ phô diễn một sự thực khách quan những tư tưởng gia vĩ đại của nhân loại như Phật Thích Ca, Đức Khổng Tử, Socrates, Aristotle, Mohammed, Martin Luther, Galilei, Newton, Rousseau, Darwin, Karl Marx và Gandhi, cũng như sẽ phác hoạ hành trình tư tưởng con người từ Đông sang Tây và từ Cổ đến Cận Đại. ',
		N'NXB Văn Hóa', N'Những Vĩ Nhân Thay Đổi Thế Giới, có người là giáo chủ tôn giáo, triết gia, khoa học gia, v.v… nhưng tất cả đều có chung một tiêu chuẩn, định nghĩa và giải thích vũ trụ, nhân sinh theo sự khám phá, sáng tạo của họ để hướng dẫn, mở mắt cho loài người đến những chân trời mới mẻ huy hoàng hơn.

Thám hiểm vào Những Vĩ Nhân Thay Đổi Thế Giới chúng ta thấy rằng, có một tư tưởng vĩ đại là một chuyện khó, đúc kết tư tưởng trở thành hệ thống lại càng khó khăn hơn, nhưng thực hiện tư tưởng là cả một vấn đề. Vĩ nhân là gì? Đó là người có sức mạnh tinh thần vô địch và sự đam mê tràn đầy. Họ chỉ là những con người như những con người tầm thường, nhưng khác hơn là họ vượt lên trên mọi cái tầm thường của con người để trở nên bất diệt với thời gian.

Triết gia người Đức, Nietzsche qua nhân vật Zarathoustra có nói “Đừng bao giờ giấu mặt vào cát của sự vật trên trời, mà phải ngạo mạn ngẩng đầu lên, một cái đầu trần gian sẽ sáng tạo các ý nghĩa trần gian”.

Phải chăng, Những Vĩ Nhân Thay Đổi Thế Giới đáng là một quyển sách gối đầu chờ các bạn Sinh Viên Học Sinh?',
		N'Lịch Sử - Chính Trị'),
	(N'284 Anh Hùng Hào Kiệt Của Việt Nam',N'Vũ Thanh Sơn',N'Giới thiệu về những nhân vật lịch sử kiệt xuất trong lịch sử dân tộc Việt Nam, có những người sống trong vinh quang, cũng có những người yên tư trong thầm lặng. Trong đó nhiều nhân vật mà các cuốn sách đã xuất bản từ trước đến nay chưa hề được đề cập đến.',
	 N'NXB Văn Hóa', N'Bằng công trình của mình không những tác giả đã đóng góp phần bổ sung cho những thiếu vắng của lịch sử nước nhà (đặc biệt là về nhân vật và sự kiện) mà còn góp phần tuyên truyền, giáo dục những tấm gương tiêu biểu cho sự nghiệp cách mạng của dân tộc - đó là sự nghiệp đấu tranh chống ngoại xâm giành lại độc lập tự do cho Tổ quốc. Chân dung các nhân vật lịch sử Việt Nam trong thời kỳ lịch sử cận đại được tác giả Vũ Thanh Sơn tập trung khắc họa ở 3 nhóm chính tương ứng với ba thời kỳ sôi động của lịch sử Việt Nam, đã có biết bao anh hùng, nghĩa sĩ hô hào nhân dân vũ trang đánh đuổi giặc Pháp: Đó là thời kỳ đầu tranh chống thực dân Pháp xâm lược cuối thế kỷ XIX (từ khi liên quân Pháp - Tây Ban Nha nổ súng tại bán đảo Sơn Trà, chính thức mở đầu cuộc chiến tranh xâm lược Việt Nam 1/9/1858). Là phong trào Cần Vương với nhiều cuộc khởi nghĩa tiêu biểu của văn thân chông Pháp (1885-1896) phát triển rộng khắp từ Nam ra Bắc. Và còn là đấu tranh chống Pháp đầu thế kỷ XX với ý thức của hệ tư tưởng mới với các phong trào chống thuế ở Quảng Nam, phong trào Duy tân, Đông du dưới sự lãnh đạo của Phan Bội Châu, Phan Chu Trinh...).',
	 N'Lịch Sử - Chính Trị'),
	(N'Khám Phá Thế Giới Tâm Linh',N'Gary Zukav',N'Bằng những trải nghiệm sống động từ chính cuộc đời mình, cùng với đầu óc khoa học cởi mở, tiếp thu cả những tinh hoa triết lý phương Đông và phương Tây, tác giả Gary Zukav muốn chia sẻ với mọi người những hiểu biết về thế giới nội tâm của con người qua một cách nhìn khác, “nhận thức đa giác quan” (multisensory perception) – một cách nhìn mà ai cũng cảm giác là có thật, nhưng chúng ta còn hiểu biết về nó rất lờ mờ và trước đây chưa ai có thể gọi tên hay mô tả một cách cụ thể.',
	 N'NXB Văn Hóa',N'Lâu nay, khái niệm tâm linh (spirituality) thường mang đậm sắc màu huyền hoặc, kỳ bí. Nhắc đến tâm linh, chúng ta lập tức nghĩ đến linh hồn, sự tái sinh, Nhân – Quả, Nguồn Năng lượng Tối cao,… như nguồn dữ liệu sẵn có bên trong tiềm thức. Song chúng ta chưa hiểu hết những triết lý tuyệt vời này, ý nghĩa quan trọng của chúng đối với một cuộc sống bình an, hạnh phúc, viên mãn – củng cố sức mạnh đích thực, sức mạnh nội tâm. Đôi khi chúng ta cũng tin, nhưng chỉ là một niềm tin… mù quáng. Đó là lý do vì sao con người vẫn còn nếm trải những đau khổ trong cuộc sống.',
	 N'Văn Hóa - Tôn Giáo'),
	(N'Sắp Xếp Nhà Cửa Theo Phong Thủy',N'Lillian Too',N'Đây là cuốn sách hướng dẫn cách sắp xếp, bài trí nhà cửa nhằm đem lại sự hài hòa tổng thể cho ngôi nhà và sức sống mới cho gia đình bạn. Tác giả đã đưa ra 162 cách sắp đặt nhà cửa liên quan đến sức khỏe thể chất và tinh thần của con người bằng cách vận dụng các nguyên lý phong thủy như khí, năng lượng, các yếu tố tự nhiên, khoa học.',
	 N'NXB Văn Hóa', N'Cuốn sách cũng chỉ rõ mối liên hệ mật thiết giữa cách sắp xếp nhà cửa với sức khỏe cảu mọi thành viên trong gia đình bạn. Vì vậy, hiểu và ứng dụng đúng phong thủy để sắp xếp nhà cửa sẽ giúp bạn có được sức khỏe và tinh thần tốt, hạn chế những nguyên nhân dẫn đến trạng thái đau khổ, căng thẳng, bực bội hay khơi gợi những ký ức đau buồn dễ làm bạn trở nên trầm uất, kiệt sức. Đây là cuốn sách mang tính thực tế, ứng dụng cao giúp bạn có được niềm vui, hạnh phúc và sức sống trong chính ngôi nhà của mình.',
	 N'Tử Vi - Phong Thủy');
-- sách kỹ năng sống--
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES (N'Bí Mật Hành Trình Tình Yêu',N' David Niven',N'Cùng cuốn sách khám phá những bí mật của hành trình của Tình Yêu, để tự rut ra cho bản thân những hành trang, kinh nghiệm cần thiết trong việc lựa chọn bạn đời.',
		N'NXB Văn Hóa',N'Phần lớn các cuộc hôn nhân được hình thành từ cảm xúc đặc biệt được gọi là tình yêu. Và như một quy luật tất yếu, hôn nhân và tình yêu luôn song hành với nhau để duy trì hạnh phúc gia đình. Khi yêu, người ta sống bằng cảm xúc, nhưng khi kết hôn thì tình yêu lại trở thành chất xúc tác để nuôi dưỡng hôn nhân, duy trì những cảm xúc tuyệt vời của thuở yêu nhau.

Và con đường đi đến hạnh phúc trong hôn nhân rất gian khổ. Mâu thuẫn trong trong quan hệ hôn nhân rất đa dạng và phức tạp, không tuân theo nguyên tắc hay tiêu chí cụ thể nào. Vì thế, không ai có thể đảm bảo chắc rằng mình đã tìm được cuộc hôn nhân hạnh phúc trọn vẹn dù trước đó đã được tư vấn, học hỏi và tích lũy một số kinh nghiệm.

Hạnh phúc trong hôn nhân cũng giống như giọt nước rơi vào lòng bàn tay, nếu nắm chặt tay lại, giọt nước sẽ được được ấp ủ và ấm dần lên, nhưng nếu cứ để tự nhiên thì giọt nước sẽ trở nên lạnh lẽo và sớm biến mất. Vì vậy, xin hãy luôn biết nâng niu và nuôi dưỡng hôn nhân bằng tất cả tình cảm yêu thương và trân trọng.',
		N'Tâm Lý - Kỹ Năng Sống'),
	(N'Nghệ Thuật Đàm Phán', N'Donald J. Trump - Tony Schwartz',N'Quyển sách cho chúng ta thấy cách Trump làm việc mỗi ngày - cách ông điều hành công việc kinh doanh và cuộc sống - cũng như cách ông trò chuyện với bạn bè và gia đình, làm ăn với đối thủ, mua thành công những sòng bạc hàng đầu ở thành phố Atlantic, thay đổi bộ mặt của những cao ốc ở thành phố New York... và xây dựng những tòa nhà chọc trời trên thế giới.',
	 N'NXB Văn Hóa',N'Quyển sách đi sâu vào đầu óc của một doanh nhân xuất sắc và khám phá một cách khoa học chưa từng thấy về cách đàm phán một thương vụ thành công. Đây là một cuốn sách thú vị về đàm phán và kinh doanh – và là một cuốn sách nên đọc cho bất kỳ ai quan tâm đến đầu tư, bất động sản và thành công.',
	 N'Tâm Lý - Kỹ Năng Sống'),
	(N'Tuổi Trẻ Đáng Giá Bao Nhiêu',N'Rosie Nguyễn',N'"Tuổi trẻ đáng giá bao nhiêu? được tác giả chia làm 3 phần: HỌC, LÀM, ĐI.',
	N'NXB Văn Hóa', N'"Bạn hối tiếc vì không nắm bắt lấy một cơ hội nào đó, chẳng có ai phải mất ngủ.

Bạn trải qua những ngày tháng nhạt nhẽo với công việc bạn căm ghét, người ta chẳng hề bận lòng.

Bạn có chết mòn nơi xó tường với những ước mơ dang dở, đó không phải là việc của họ.

Suy cho cùng, quyết định là ở bạn. Muốn có điều gì hay không là tùy bạn.

Nên hãy làm những điều bạn thích. Hãy đi theo tiếng nói trái tim. Hãy sống theo cách bạn cho là mình nên sống.

Vì sau tất cả, chẳng ai quan tâm."',
	N'Tâm Lý - Kỹ Năng Sống'),
	(N'55 Cách Để Tự Tin',N'XACT',N'Trong cuốn sách mà bạn đang cầm trên tay, chúng tôi đưa ra 55 cách để bạn có thể luôn tự tin, hi vọng rằng, cuốn cẩm nang nhỏ này sẽ giúp bạn xây dựng và nâng cao sự tự tin của mình.',
	 N'NXB Văn Hóa',N'Tự tin là tố chất quan trọng để chúng ta có được thành công trong cuộc sống. Người tự tin sẽ luôn nhận được sự chú ý, yêu mến, tôn trọng và tin cậy từ phía các nhà tuyển dụng và đối tác, vì họ có khả năng và ý chí phấn đấu để hoàn thành công việc ngay cả trong những tình huống khó khăn, cấp bách. Họ có mối quan hệ bạn bè rộng rãi, được những người xung quanh ngưỡng mộ và kính trọng. Chính vì thế, các bậc phụ huynh đều mong muốn con em mình lớn lên trở thành những người tràn đầy lòng tự tin.

     Tự tin không phải là tố chất bẩm sinh, mà nó là kết quả mỗi cá nhân tự đúc rút ra được trong quá trình sống, trải nghiệm của mình. Một người tự tin sẽ luôn tin tưởng vào khả năng, sức mạnh và năng lực của bản thân. Họ không bị áp lực bởi những hoài nghi và mặc cảm tự ti, cũng như không áp đặt ý tưởng và niềm tin cho người khác.',
	 N'Tâm Lý - Kỹ Năng Sống'),
	(N'55 Cách Để Sử Dụng Tốt Ngôn Ngữ Cơ Thể','XACT',N'Cuốn sách nhỏ mà bạn đang cầm trên tay này xin được giới thiệu tới bạn đọc 55 cách sử dụng ngôn ngữ cơ thể để có thể gây ấn tượng và đạt hiệu quả cao trong giao tiếp.',
	 N'NXB Văn Hóa',N' Con người thường sử dụng hầu hết các ngôn ngữ cơ thể của mình trong giao tiếp. Đơn giản như khi nói chuyện, bạn không chỉ giao tiếp bằng lời nói mà còn giao tiếp bằng ánh mắt. Nói cách khác, nếu bạn nhìn vào mắt đối phương khi đang trò chuyện, việc giao tiếp sẽ có hiệu quả tốt hơn. Nhìn chung, ngôn ngữ cơ thể thường được bộc lộ một cách vô thức. Nếu bạn biết rèn luyện để sử dụng chúng có mục đích hơn, bạn sẽ gây được ấn tượng tốt với mọi người. Sự thể hiện ngôn ngữ cơ thể tuỳ thuộc vào các tình huống, văn hoá và mối quan hệ của những người đang giao tiếp với nhau. Tóm lại, ngôn ngữ cơ thể được sử dụng để biểu lộ cảm xúc.',
	 N'Tâm Lý - Kỹ Năng Sống');
-- sách văn học Việt Nam
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES (N'Tuyển Tập Truyện Ngắn Vũ Trọng Phụng',N'Vũ Trọng Phụng',N'Những tập truyện ngắn nổi tiếng của nhà văn Vũ Trọng Phụng được chúng tôi sưu tầm và gửi tới các bạn đọc, như Đời là một cuộc chiến đấu, Gương... tống tiền, Lấy vợ xấu, Tết ăn mày,...',
		N'NXB Văn Học',N'Với giọng văn sắc sảo, mang đậm chất châm biếm, trào phúng và nội dung tư tưởng sâu sắc, các tác phẩm của Vũ Trọng Phụng đều hướng tới chủ đề hiện thực, tố cáo và vạch trần xã hội Việt Nam trước cách mạng tháng 8 - một xã hội bê bối với những tấn trò đời bi kịch. Đọc những trang văn của ông, người ta không khỏi ngậm ngùi, chua chát. Ra đi vì bệnh tật khi mới 27 tuổi đời, khi tài năng đang độ phát triển rực rỡ, Vũ Trọng Phụng để lại trong làng văn và trong lòng độc giả một chỗ trống không dễ gì khỏa lấp.',
		N'Truyện ngắn'),
	(N'Tắt Đèn', N'Ngô Tất Tố',N'Một tác phẩm văn học nổi tiếng bậc nhất của Ngô Tất Tố. Đây là một tác phẩm văn học hiện thực phê phán với nội dung nói về cuộc sống khốn khổ của tầng lớp nông dân Việt Nam đầu thế kỉ XX dưới ách đô hộ của thực dân Pháp.',
	 N'NXB Văn Học',N'Mở đầu tác phẩm là không khí căng thẳng, ngột ngạt của một làng quê trong những ngày sưu thuế. Cổng làng đóng lại, công việc cày bừa đình đốn, bọn lý trưởng, trương tuần chửi bới, quát tháo om sòm; Mấy tên cai lệ, lính cơ tay thước, roi song, dây thừng đi tróc người thiếu thuế. Tiếng trống, mõ, tù và inh ỏi, tiếng thét lác, đánh đập, tiếng kêu khóc thảm thiết vang lên như trong một cuộc săn người.

Gia đình chị Dậu thuộc loại “nhất nhì trong hạng cùng đinh” nên mấy hôm nay, chị phải chạy vạy ngược xuôi để có tiền nộp suất sưu cho anh Dậu. Bọn nhà giàu chẳng những không cho chồng chị vay mượn mà còn nhiếc móc, đe dọa. Anh Dậu đang ốm cũng bị bọn tay sai xông đến đánh trói, lôi ra đình cùm kẹp. Chị đành phải rứt ruột đem cái Tý, đứa con gái đầu lòng lên bảy bán cho lão Nghị Quế bên thôn Đoài. Vợ chồng lão giàu có mà keo kiệt, tàn ác, đã lợi dụng tình cảnh khốn cùng của chị, mua cái Tý và cả một ổ chó mà chỉ trả hai đồng bạc! Cộng với mấy hào bán gánh khoai, chị tưởng vừa đủ nộp suất sưu và chồng được tha về; Ngờ đâu, bọn lý dịch lại bắt chị phải nộp cả suất sưu của người em chồng đã chết từ năm ngoái! Thật là cùng đường. Giữa đình làng, tiếng kêu uất ức của chị vang lên thảm thiết.',
	 N'Tiểu thuyết'),
	(N'Truyện Kiều',N'Nguyễn Du',N'Truyện Kiều là tiểu thuyết viết bằng thơ lục bát. Truyện phản ánh xã hội đương thời thông qua cuộc đời của nhân vật chính Vương Thuý Kiều. Xuyên suốt tác phẩm là chữ "tâm" theo như Nguyễn Du đã tâm niệm "Linh Sơn chỉ tại nhữ tâm đầu" (nghĩa là "Linh Sơn chỉ ở lòng người thôi"). Ngày nay, Truyện Kiều của Nguyễn Du là một trong những tác phẩm văn học Việt Nam được giới thiệu rộng rãi nhất đến với các du khách cũng như các nhà nghiên cứu nước ngoài.',
	 N'NXB Văn Học',N' 
Đầu lòng hai ả tố nga,
Thúy Kiều là chị em là Thúy Vân.
Mai cốt cách, tuyết tinh thần,
Mỗi người một vẻ, mười phân vẹn mười.',
	N'Tiểu thuyết - thơ lục bát'),
	(N'Chí Phèo', N'Nam Cao', N'Truyện ngắn Chí Phèo, nguyên có tên là Cái lò gạch cũ; khi in thành sách lần đầu năm 1941, Nhà Xuất bản Đời mới – Hà Nội tự ý đổi tên là Đôi lứa xứng đôi. Đến khi in lại trong tập Luống cày (do Hội Văn hóa cứu quốc xuất bản, Hà Nội, 1946), Nam Cao đặt lại tên là Chí Phèo.[1]',
	 N'NXB Văn Học',N'Tác phẩm Chí Phèo mang giá trị nhân đạo sâu sắc, thể hiện tấm lòng yêu hương, trân trọng của Nam Cao đốI vớI những ngườI khốn khổ. Chí Phèo còn là tiếng kêu cứu thiết tha của những ngướI bất hạnh. Hãy bảo vệ và đấu tranh cho quyền được làm ngườI của những con ngườI kương thiện. Họ phảI được sống và sống hạnh phúc, không còn những thế lực đen tốI của xã hộI đẩy họ vào chổ cùng khốn, bế tắc, đầy bi kịch xót xa…',
	 N'Truyện ngắn'),
	(N'Vang bóng một thời',N' Nguyễn Tuân',N'Vang Bóng Một Thời viết năm 1940 đã được dư luận chung coi như một trong những tác phẩm hay và nổi tiếng nhất của nền văn chương Việt Nam, cũng là một trong những tác phẩm được nhắc tới nhiều nhất, được coi như ngang hàng với Hồn Bướm Mơ Tiên của Khái Hưng, Ðôi Bạn của Nhất Linh',
	 N'NXB Văn Học',N'Tập truyện đã làm sống lại cả một thời phong kiến đã qua với những nghệ thuật cổ thanh cao, những nếp sống, sinh hoạt xã hội nho phong của một nền văn minh xưa cũ, nó cũng là niềm nuối tiếc của một tâm hồn hoài cổ trước những cái hay, cái đẹp, những nghệ thuật cầu kỳ của một thời đại đã qua, cái thời ấy nay đã chết rồi, chỉ còn để lại một tiếng vang:

...Vang Bóng Một Thời.',
	 N'Truyện ngắn - Tùy bút');
-- sách chuyên ngành
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES(N'Giáo Trình Thuật Toán -Introduction To Algorithms',' Thomas H. Cormen - Charles E. Leiserson','Quyển sách đề cập đến các thuật toán theo một diện rộng và sâu sắc, nhưng vẫn được trình bày sao cho mọi đối tượng độc giả đều có thể tiếp thu được. Mỗi chương là một đơn vị kiến thức độc lập và có thể làm thành một bài học giảng dạy.',
		N'NXB Thống Nhất',N'Các thuật toán được mô tả bằng tiếng Anh dưới dạng mã giả mà bất kỳ ai biết ít nhiều về lập trình cũng có thể hiểu được. Trong khi đó, phần diễn giải được trình bày một cách đơn giản nhất nhưng vẫn không thiếu chiều sâu và những chứng minh toán học chặt chẽ.

Được sự quan tâm của rất nhiều lập trình viên và sinh viên các trường đại học trên khắp thế giới, ấn bản thứ hai (second edition) của Introduction to Algorithms đã thật sự tạo nên một hiện tượng trong lĩnh vực thuật toán. Với tính chất rõ ràng, toán học chặt chẽ, trong khi vẫn có thể tiếp cận được bởi những người không am hiểu toán học, quyển sách đã thiết lập nên một tiêu chuẩn cho sách giáo khoa; đồng thời là tài liệu tham khảo tốt nhất cho việc giải những bài toán tin học.',
		N'Công Nghệ Thông Tin'),
	(N'Machine Learning Cơ Bản',N'Vũ Hữu Tiệp',N'Machine Learning là một tập con của AI. Theo định nghĩa của Wikipedia, Machine learning is the subfield of computer science that “gives computers the ability to learn without being explicitly programmed”. Nói đơn giản, Machine Learning là một lĩnh vực nhỏ của Khoa Học Máy Tính, nó có khả năng tự học hỏi dựa trên dữ liệu đưa vào mà không cần phải được lập trình cụ thể.',
	 N'NXB Thống Nhất',N'Trí Tuệ Nhân Tạo đang len lỏi vào mọi lĩnh vực trong đời sống mà có thể chúng ta không nhận ra. Xe tự hành của Google và Tesla, hệ thống tự tag khuôn mặt trong ảnh của Facebook, trợ lý ảo Siri của Apple, hệ thống gợi ý sản phẩm của Amazon, hệ thống gợi ý phim của Netflix, máy chơi cờ vây AlphaGo của Google DeepMind, …, chỉ là một vài trong vô vàn những ứng dụng của AI/Machine Learning. 
	 
	 Những năm gần đây, khi mà khả năng tính toán của các máy tính được nâng lên một tầm cao mới và lượng dữ liệu khổng lồ được thu thập bởi các hãng công nghệ lớn, Machine Learning đã tiến thêm một bước dài và một lĩnh vực mới được ra đời gọi là Deep Learning (Học Sâu - thực sự tôi không muốn dịch từ này ra tiếng Việt). Deep Learning đã giúp máy tính thực thi những việc tưởng chừng như không thể vào 10 năm trước: phân loại cả ngàn vật thể khác nhau trong các bức ảnh, tự tạo chú thích cho ảnh, bắt chước giọng nói và chữ viết của con người, giao tiếp với con người, hay thậm chí cả sáng tác văn hay âm nhạc.',
	 N'Công Nghệ Thông Tin'),
	(N'Thế Giới Lượng Tử Kỳ Bí','Silvia Arroyo Camejo',N'Vật lí lượng tử, một trò chơi đầy tinh tế và rắc rối, một thế giới chứa đầy những nghịch lí và những bất ngờ không thể đoán định. Nhưng nó cũng không phức tạp đến mức làm ta nản chí, tất cả đều nhờ cuốn sách Thế giới lượng tử kỳ bí của Silvia Arroyo Camejo.',
	 N'NXB Tuổi Trẻ',N'Thực ra ánh sáng là gì? Giả thuyết lượng tử đến từ đâu?...

Là những câu hỏi gợi mở đầu mỗi chương sách nhằm giúp người đọc có một cái nhìn nhỏ nhoi vào thế giới lượng tử đầy bí hiểm.

Cuốn sách hứa hẹn sẽ mang đến cho bạn - những độc giả có niềm đam mê với những siêu vi hạt - một cái nhìn hết sức quyến rũ về vật lí lượng tử.',
	N'Vật Lý Lượng Tử'),
	(N'Sức Bền Vật Liệu Và Kết Cấu',N'Nguyễn Đình Đức - Đào Như Mai',N'Tài liệu chuyên ngành Kiến trúc - Xây dựng',
	 N'NXB Khoa học và Kỹ thuật',N'Sức bền vật liệu là môn học cơ sở quan trọng, cung cấp cho người học những kiến thức cơ bản nhất để giải các bài toán liên quan đến hệ thanh, tính toán sức bền của vật liệu và kết cấu. Chính vì vậy sức bền vật liệu và cơ học kết cấu được giảng dạy cho sinh viên tất cả các trường đại học kỹ thuật',
	 N'Kiến Trúc - Xây Dựng'),
	(N'Nghệ Thuật Chinh Phục Khách Hàng','Janelle Barlow',N'Trong Nghệ thuật Chinh Phục Khách Hàng bậc thầy Janelle Barlow và Claus Moller đã giới thiệu một ý tưởng đột phá mang tính cách mạng: Biến những lời phàn nàn, chỉ trích thành một món quà. ',
	 N'NXB Tuổi Trẻ',N'“Ấn bản đầu tiên của quyển sách đã là một viên ngọc quý. Phiên bản mới được cập nhật toàn diện này còn tuyệt vời hơn nữa. Những ví dụ mới vừa mang tính chất chỉ dẫn vừa truyền cảm hứng và thực sự hữu ích cho những người làm công tác chăm sóc khách hàng. Tôi tin rằng quyển sách sẽ gây sửng sốt cho bất cứ ai đang nghi ngờ sức mạnh từ việc lắng nghe khách hàng của mình”...',
	 N'Marketing - Bán hàng');
--sách văn học nước ngoài
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES(N'Đônkihôtê - nhà quý tộc tài ba xứ Mantra', 'Xervantex',N'Đônkihôtê - nhà quý tộc tài ba xứ Mantra là tác phẩm xuất sắc nhất của Mighel De Xervantex Xaavedra, ra đời năm 1605.',
		N'NXB Văn Học',N'Nội dung cuốn tiểu thuyết xoay quanh những chuyến phiêu lưu của một lão quý tộc già sống ở xứ Mancha tên là Alonso Quixano, lão đọc nhiều truyện hiệp sĩ đến mức mất trí và quyết định trở thành một kỵ sĩ (caballero andante) để làm sống lại tinh thần hào hiệp và phụng sự cho quốc gia. Lão tự đổi tên mình thành Don Quijote xứ Mancha. Lão cũng nhận một gã nông dân quê mùa, Sancho Panza, làm người tháp tùng, người thường sử dụng sự lanh lợi, tinh ranh trần tục để đối phó với những lời độc thoại hùng tráng của Don Quijote về vấn đề hiệp sĩ, thứ mà lúc đó đã được coi là lỗi thời. Trong phần đầu tiên của cuốn sách, Don Quijote không phân biệt được đâu là thực đâu là ảo, chỉ đắm chìm trong thế giới hiệp sĩ mà mình tưởng tượng ra.',
		N'Tiểu Thuyết Phương Tây'),
	('Robinson Crusoe','Daniel Defoe',N'"Robinson Crusoe là tiểu thuyết của nhà văn Anh Daniel Defoe (1660-1731), tên tiếng Anh đầy đủ: The life and strange surprizing adventures of Robinson Crusoe of York, Mariner (nghĩa tiếng Việt: Cuộc đời và những chuyện phiêu lưu kỳ thú của Robinson Crusoe, người thủy thủ xứ York).',
	 N'NXB Văn Học',N'Robinson là người ưa hoạt động và ham thích phiêu lưu, say sưa đi đến những miền đất lạ, bất chấp sóng to gió lớn và những nỗi hiểm nguy. Ngày 1 tháng 9 1651, khi được 19 tuổi, Robinson xuống tàu tại hải cảng Hull của Anh để cùng một người bạn sửa soạn đi London. Cuộc hành trình không trót lọt, sóng to gió lớn khiến tàu bị đắm ở Yacmao. Tai họa ấy không làm Robinson nhụt chí. Cha mẹ khóc lóc, bạn bè can ngăn không lay chuyển được quyết tâm của Robinson tiếp tục thực hiện nhiều cuộc phiêu lưu khác. Khi đi buôn tại bờ biển Guinea châu Phi, Robinson bị một tên cướp biển người Thổ Nhĩ Kỳ bắt bán làm nô lệ. Trải qua nhiều gian nan, Robinson trốn thoát và chạy qua Brasilia, thuộc Nam Mỹ làm nghề trồng mía...',
	 N'Tiểu Thuyết Phương Tây'),
	(N'Pippi tất dài','Astrid Lindgren',N'Câu chuyện kể về cô bé kỳ lạ mặt đầy tàn nhang, chưa bao giờ đi học và khoẻ đến nỗi nhấc được cả một con ngựa này đã được xuất bản tại hơn 90 nước và bảy lần dựng thành phim tại các quốc gia khác nhau, trở thành một trong những nhân vật kinh điển được yêu quý nhất của văn học thiếu nhi châu Âu và thế giới.',
	 N'NXB Văn Học', N'Cô bé Pippi mất mẹ từ nhỏ và người cha đã mất tích ngoài biển. Pippi không xinh đẹp, với mái tóc đỏ hoe tết thành hai cái đuôi sam cứng như hai cái que, mũi to như quả cà chua và khuôn mặt đầy tàn nhang. Trông cô rất lôi thôi nhếch nhác và ăn mặc thì chẳng hợp chút nào hết. Cô lúc nào cũng ầm ĩ và quả quyết – đi đến đâu người ta cũng nhận ra cô, nghe thấy tiếng cô nói và biết cô muốn gì. Nhưng Pippi luôn luôn bảo vệ các bạn của mình mỗi khi họ gặp nguy hiểm. Thế giới là cả một sân chơi và cuộc sống là cả một cuộc thám hiểm khổng lồ khi có Pippi ở đó.

Hai người bạn thân của Pippi là Thomas và Annika, sống tại ngôi nhà bên cạnh. Ở cùng với Pippi là con khỉ có tên là ông Nilsson và con ngựa được Pippi cho đứng trong hành lang.

Cô cũng đã thu xếp cuộc sống của mình, biết làm bánh nướng tuyệt ngon, biết tổ chức một cuộc du ngoạn hoàn hảo, biết lên kế hoạch cho buổi liên hoan sinh nhật độc đáo...Pippi đã chứng tỏ cho người lớn thấy, dù không có sự kèm cặp của cha mẹ, một đứa trẻ vẫn có thể làm mọi việc và sống tuyệt vời đến thế nào khi được hoàn toàn tự do và sống theo ý thích của mình.',
	 N' Tiểu Thuyết Phương Tây'),
	(N'Tây Du Ký',N'Ngô Thừa Ân','Tây Du Ký là bộ tiểu thuyết thần thoại thành công nhất trong lịch sử cổ đại Trung Quốc.',
	 N'NXB Văn Học',N'Tiểu thuyết lấy câu chuyện Đường Tăng (Sư cụ Huyền Trang) - sư cụ Phật học nổi tiếng Trung Quốc thế kỷ 7 công nguyên sang Ấn Độ thỉnh kinh làm cốt truyện, hư cấu sư cụ Đường Tăng và ba học trò gặp phải các loại gian nan trắc trở trên con đường đi thỉnh kinh, xây dựng thành công nhân vật Tôn Ngộ Không - một hình tượng con khỉ thần không sợ bất cứ quyền uy gì, đấu tranh không ngừng với các thế lực xấu, bày tỏ nguyện vọng của tác giả đối với cuộc sống hiện thực.',
	 N'Tiểu Thuyết Trung Quốc'),
	(N'Thuỷ Hử',N'Thi Nại Am',N'Thủy hử hay Thủy hử truyện, nghĩa đen là "bến nước", là một tác phẩm trong bốn tác phẩm lớn của văn học cổ điển Trung Hoa, thường gọi là nhóm Tứ đại danh tác. ',
	 N'NXB Văn Học',N'Các anh hùng Lương Sơn Bạc thường được nhắc tới gồm có 108 người, tuy nhiên trên thực tế phải là 109 người, nếu tính cả Tiều Cái. Xa hơn nữa, chủ trại đầu tiên là Vương Luân, song Vương Luân nhanh chóng bị trừ khử không đóng vai trò gì đối với sự phát triển của Lương Sơn Bạc và do đó không được nhắc tới và không được tính vào hàng ngũ các anh hùng Lương Sơn.

Mặc dù Tiều Cái không chính thức thuộc về 108 anh hùng Lương Sơn Bạc vì trúng tên chết sớm ở trại Tăng Đầu, nhưng xét ra Tiều Cái là người lãnh tụ đầu tiên từ lúc Lương Sơn mới mở. Đối với các anh hùng Lương Sơn Bạc, từ Tống Giang trở đi, Tiều Cái là thủ lĩnh tối cao và nếu không vì cái chết của Tiều Cái, Tống Giang có thể không trở thành thủ lĩnh của Lương Sơn

108 thủ lĩnh Lương Sơn gồm có 36 vị sao Thiên Cương và 72 vị Địa sát, đứng đầu là Tống Giang và cuối cùng là Đoàn Cảnh Trụ.',
	 N'Tiểu Thuyết Trung Quốc');
--sách thiếu nhi
INSERT INTO Book (book_title,author,brief,publisher,content,category)
VALUES(N'Truyện cổ Andersen','Hans Christian Andersen',N'Nhà văn Hans Christian Andersen (1805 - 1875) có lẽ là một hiện tượng văn học hiếm có trên thế giới.. Andersen viết du ký, kịch, tiểu thuyết, làm thơ, nhưng nổi nhất là truyện. Truyện của ông dựa vào truyện dân gian, truyền thuyết, lịch sử, đời sống hàng ngày và cả cuộc đời riêng của tác giả.',
	   N'NXB Văn Học',N'Truyện Cổ Andersen là tập hợp các tác phẩm của nhà văn Đan Mạch Hans Christian Andersen như: Bà chúa tuyết, cô bé tí hon, bộ quần áo mới cảu hoàng đế.

Không chỉ đơn thuần là kể chuyện, tác giả còn muốn đem đến cho người đọc những bài học quý giá. Với lối viết chuyện lôi cuốn, hấp dẫn, Andersen đưa người đọc lạc vào thế giới của thế giới cổ tích đầy kì bí. Kết thúc mỗi câu chuyện lại để lại cho người đọc một bài học hay về cuộc sống.',
		N'Cổ Tích - Thần Thoại'),
	(N'Người Cá','Alexander Romanovich Belyaev',N'Câu chuyện được viết hấp dẫn, gợi trí tò mò của bạn đọc. Bên cạnh ước mơ được làm chủ biển cả của con người, truyện còn ca ngợi những tấm lòng nhân hậu, trung thực, phụng sự hết mình cho khoa học để cứu giúp nhân loại và bà mẹ tự nhiên.',
	 N'NXB Văn Học',N'Dân làng ven vịnh Plata xôn xao về chuyện con quỷ biển xuất hiện, ai nấy đều sợ đến mức không dám ra khơi. Tin đồn lan tới tận thủ đô Buenos Aires, thu hút giới báo chí chuyên săn lùng những tin giật gân thỏa mãn độc giả hiếu kì. Nhưng vẫn không ai biết thực hư như thế nào. Mọi chuyện có thể sẽ chỉ còn được nghe như là tin đồn nếu một chủ thuyền ngọc trai đang làm ăn trên vịnh là Zurita không cố công tìm kiếm dấu tích của con quỷ để bắt nó mò ngọc trai cho lão. Theo sự lần dấu của Zurita người đọc cũng dần dần khám phá thực sự quỷ biển là ai. Mọi chuyện bắt đầu xấu đi khi chàng “quỷ biển” lần đầu biết yêu!',
	 N'Phiêu Lưu - Mạo Hiểm'),
	(N'Chú Bé Rắc Rối',N'Nguyễn Nhật Ánh',N'Không biết các bạn như thế nào, chứ tôi thì tôi chưa từng lo cho ai bao giờ. Tôi lo cho chính tôi còn chưa xong nữa là. (mới mở đầu truyện thì nhân vật chính đã tâm sự như vậy rồi, nghe thấy RẮC RỐI). Thực sự thì làm sao, vẫn là giọng văn “đọc tức cười”, cách thắt nút, mở nút đầy bất ngờ của tác giả dẫn ta đi hết rắc rối này đến rắc rối khác. Đọc rắc rối nhưng mà thấy thú vị.',
	 N'NXB Tuổi Trẻ',N'Không biết các bạn thế nào, chứ tôi thì tôi chưa từng lo cho ai bao giờ. Tôi lo cho chính tôi còn chưa xong nữa là. Thật đúng hệt như má tôi nhận xét:
- Thân mày, mày còn không biết lo, chẳng hiểu khi lớn lên mày làm được việc gì!
Đó là những lời má tôi than vãn trong lúc đang kỳ cọ tắm rửa cho tôi. Mà tôi thì nào có nhỏ nhít gì, mười ba tuổi rồi, học sinh lớp bảy đàng hoàng đấy chứ. Nói cho đúng ra thì tôi thích tự mình tắm hơn, tha hồ vùng vẫy, nghịch nước bao lâu tùy thích, chẳng ai cấm cản. Nhưng tôi thích là một chuyện, còn má tôi có thích hay không lại là một chuyện hoàn toàn khác. Mỗi lần tôi từ nhà tắm bước ra, đầu cổ còn ướt mem, chưa kịp lỉnh ra sân chơi là má tôi đã chộp ngay lấy tay tôi, kéo lại gần:
- Lại đây má coi nào!',
	 N'Truyên Teen - Tuổi Học Trò'),
	(N'Tôi Thấy Hoa Vàng Trên Cỏ Xanh',N'Nguyễn Nhật Ánh',N'Ta bắt gặp trong Tôi Thấy Hoa Vàng Trên Cỏ Xanh một thế giới đấy bất ngờ và thi vị non trẻ với những suy ngẫm giản dị thôi nhưng gần gũi đến lạ. Câu chuyện của Tôi Thấy Hoa Vàng Trên Cỏ Xanh có chút này chút kia, để ai soi vào cũng thấy mình trong đó, kiểu như lá thư tình đầu đời của cu Thiều chẳng hạn... ngây ngô và khờ khạo.',
	 N'NXB Tuổi Trẻ',N'Chú Đàn bảo tôi:

- Con xòe tay ra cho chú xem nào!

Tôi co những ngón tay lại, nắm thật chặt và giấu ra sau lưng:

- Tay con sạch cơ mà. Hồi sáng con đã rửa tay rồi.

Chú Đàn phì cười:

- Chú có định khám tay con đâu. Con xòe tay ra để chú xem con có mấy cái hoa tay thôi.

Đằng sau lưng, hai bàn tay tôi lỏng đi. Tôi chìa bàn tay trái ra trước mặt chú Đàn, thắc mắc:

- Hoa tay là gì hở chú?

Chú Đàn dựng mắt nhìn tôi:

- Con lớn từng này rồi mà không biết hoa tay là gì à?

Chú cầm lấy bàn tay tôi, chậm rãi giải thích:

- Hoa tay là những vân tay hình tròn ở đầu mỗi ngón tay. Hoa tay càng nhiều thì vẽ càng đẹp. Nếu con có mười cái hoa tay, con sẽ vẽ đẹp nhất lớp. Con viết chữ cũng đẹp nhất lớp.',
	 N'Truyên Teen - Tuổi Học Trò'),
	(N'Công Chúa Tóc Mây','Walt Disney',N'Rapunzel – Công chúa tóc mây là một câu chuyện nhẹ nhàng, hóm hỉnh, lãng mạn và tràn đầy xúc động. Vượt qua câu chuyện công chúa hoàng tử như một mô típ quen thuộc của thế giới cổ tích, Walt Disney đã đem đến cho người đọc bài học cuộc sống rất giản dị mà vô cùng thấm thía.',
	 N'NXB Tuổi Trẻ',N'Câu chuyện là hành trình khám phá những bí mật của số phận nàng công chúa có suối tóc vàng óng ả, dài tới hơn 20m – Rapunzel. Vì phép thuật của mụ Tiên Ác Gothel muốn lưu giữ tuổi xuân mãi mãi, nàng công chúa tội nghiệp bị bắt cóc, xa rời cuộc sống vương tộc cùng vua và hoàng hậu, sống cuộc đời gò bó giữa tòa tháp kín bưng ròng rã 18 năm trời. Mái tóc kì diệu của nàng có khả năng tỏa sáng trong bóng tối, chữa lành mọi vết thương, lưu giữ tuổi trẻ cho con người. Rapunzel bị Mẹ Gothel cấm tiếp xúc với thế giới bên ngoài, quanh năm suốt tháng chỉ cho phép nàng ca hát, vẽ vời trong tháp ngà. Người duy nhất công chúa có thể làm bạn được là chú tắc kè Pascal. Một ngày kia, chuẩn bị đến lễ sinh nhật thứ 18 của Rapunzel, tên đạo chích có sức mê hoặc chết người Flynn Rider đã lẻn vào cung vua, ăn trộm vương miện công chúa bị mất tích năm xưa và chạy trốn tới ngọn tháp giữa rừng sâu.

Chẳng ngờ rằng, Flynn đụng độ Rapunzel. Hành trình của họ lại tiếp tục với nhiều trải nghiệm khi công chúa tóc mây đề nghị anh chàng đạo chính điển trai phải dẫn nàng khám phá hàng nghìn ngọn đèn bay tỏa sáng trong đêm, đổi lại, nàng sẽ trả vương miện cho anh. Đồng hành với đôi nam nữ là chú ngựa lai chó nóng nảy Maximus, tắc kè lai ếch Pascal và nhóm người thô lỗ nhưng đầy khát khao, ước mơ ở quán rượu trong rừng. Bao trùm câu chuyện không chỉ là những tình huống hài hước, dí dỏm mà thông điệp Walt Disney gửi gắm đến người đọc còn là bài học đầy tính nhân văn. Mỗi nhân vật trong hành trình tìm kiếm bản thể đều bộc lộ những tính cách riêng, gần gũi với cuộc sống hàng ngày.

Mẹ Gothel, thực chất là nhân vật phản diện, chỉ vì ích kỉ vô lối của mình mà nhẫn tâm giam cầm, trói buộc tâm hồn của một cô gái trẻ. Nhưng Mẹ Gothel cũng là biết bao nhiêu bà mẹ ngoài đời thực kia, luôn muốn giữ chặt những đứa con trong tầm tay mà không biết rằng, trẻ con cần phải khôn lớn, phải biết ước mơ và khát vọng khám phá thế giới kì diệu xung quanh, nhất là tìm được mục đích sống cho riêng mình. Thông qua câu chuyện đầy tính chất ngụ ngôn, Walt Disney giúp người đọc có cái nhìn phóng khoáng hơn về cuộc đời. Thế giới vô cùng phức tạp, rộng lớn và ẩn chứa cả những rủi ro. Nhưng thế giới cũng là bao la điều kì diệu mà nếu không bước chân ra ngoài, chẳng ai trong chúng ta có thể nhận thấy sự giàu có của nó.',
	 N'Truyên Teen - Tuổi Học Trò');

INSERT INTO Contain(book_case_id,book_id)
VALUES	('1','1'),('1','2'),('1','3'),('1','4'),('1','5'),
		('2','6'),('2','7'),('2','8'),('2','9'),('2','10'),
		('3','11'),('3','12'),('3','13'),('3','14'),('3','15'),
		('4','16'),('4','17'),('4','18'),('4','19'),('4','20'),
		('5','21'),('5','22'),('5','23'),('5','24'),('5','25'),
		('6','26'),('6','27'),('6','28'),('6','29'),('6','30');
