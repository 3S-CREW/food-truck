import DashboardStats from '@/components/layout/DashBoardStats';
import DashboardTabs from '@/components/layout/DashBoardTabs';
import NoticeBar from '@/components/layout/NoticeBar';
import DashboardList from '@/components/layout/DashBoardList';
import * as S from '@/pages/AdminDashBoard/style';
import { colors } from '@toss/tds-colors';
import { Text, TextButton } from '@toss/tds-mobile';
import { useState } from 'react';
import { STATS_DATA, REPORT_LIST, FOOD_TRUCK_LIST } from '@/constants/mockData';

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
        <DashboardList 
          selectedTab={selectedTab}
          reportList={REPORT_LIST}
          foodTruckList={FOOD_TRUCK_LIST}
        />
      </S.ListContainer>

      {/* 하단 안내 문구 */}
      <NoticeBar />
    </S.Container>
  );
};

export default AdminDashBoardPage;
