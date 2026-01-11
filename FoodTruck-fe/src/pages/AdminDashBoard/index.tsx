import DashboardStats from '@/components/layout/DashBoardStats';
import DashboardTabs from '@/components/layout/DashBoardTabs';
import ReportCard, { type ReportData } from '@/components/layout/ReportCard';
import * as S from '@/pages/AdminDashBoard/style';
import { colors } from '@toss/tds-colors';
import { Paragraph, Text, TextButton } from '@toss/tds-mobile';
import { useState } from 'react';

// mock 데이터 (추후에 API로 받아올때 제거)
const STATS_DATA = [
  { label: '총 신고', count: 3, color: colors.blue700, bgColor: colors.blue50 },
  {
    label: '미처리',
    count: 2,
    color: colors.orange400,
    bgColor: colors.orange50,
  },
  { label: '처리', count: 1, color: colors.green400, bgColor: colors.green50 },
];

const REPORT_LIST: ReportData[] = [
  {
    id: 1,
    truckName: '한식 트럭',
    reportType: '리뷰 신고',
    status: 'exposed',
    content: '음식이 ** 맛없어요',
    reason: '욕설 포함',
    reporter: '닉네임A',
    date: '2024-12-23',
  },
  {
    id: 2,
    truckName: '타코야끼 천국',
    reportType: '푸드트럭 신고',
    status: 'exposed',
    content: '위생 상태가 불량함',
    reason: '음식 품질 불량',
    reporter: '닉네임B',
    date: '2024-12-22',
  },
  {
    id: 3,
    truckName: '핫도그 마을',
    reportType: '푸드트럭 신고',
    status: 'hidden',
    content: '먹고 배탈났어요',
    reason: '음식 품질 불량',
    reporter: '신고자C',
    date: '2024-12-21',
  },
];

const AdminDashBoardPage = () => {
  const [selectedTab, setSelectedTab] = useState(0);

  return (
    <S.Container>
      {/* 헤더 */}
      <S.Header>
        <Text typography='t2' fontWeight='bold'>
          관리자 대시보드
        </Text>
        <TextButton size='medium' color={colors.red500}>
          로그아웃
        </TextButton>
      </S.Header>

      {/* 상단 통계 카드 */}
      <DashboardStats stats={STATS_DATA} />

      {/* 탭 */}
      <DashboardTabs selected={selectedTab} onChange={setSelectedTab} />

      {/* 리스트 영역 */}
      <S.ListContainer>
        {REPORT_LIST.map((report) => (
          <ReportCard key={report.id} data={report} />
        ))}
      </S.ListContainer>

      {/* 하단 안내 문구 */}
      <S.NoticeBar>
        <Paragraph typography='t5'>
          <Paragraph.Icon name='icon-bulb' />

          <Paragraph.Text
            typography='t6'
            color={colors.blue700}
            fontWeight='medium'
            style={{ paddingLeft: '8px' }}
          >
            숨김 처리 시 사용자에게 즉시 비노출됩니다.
          </Paragraph.Text>
        </Paragraph>
      </S.NoticeBar>
    </S.Container>
  );
};

export default AdminDashBoardPage;
