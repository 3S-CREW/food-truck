import FoodTruckCard,{ type FoodTruckData } from '@/components/layout/FoodTruckCard';
import EmptyList from '@/components/layout/EmptyList';
import ReportCard, { type ReportData } from '@/components/layout/ReportCard';

interface Props {
  selectedTab: number;
  reportList: ReportData[];
  foodTruckList: FoodTruckData[];
}

const DashboardList = ({ selectedTab, reportList, foodTruckList }: Props) => {
  // 신고 관리 탭 (Tab 0)
  if (selectedTab === 0) {
    if (reportList.length === 0) {
      return (
        <EmptyList
          iconName="icon-chat-bubble-grayline-mono"
          message="신고 목록이 여기에 표시됩니다"
        />
      );
    }
    return (
      <>
        {reportList.map((report) => (
          <ReportCard key={report.id} data={report} />
        ))}
      </>
    );
  }

  // 푸드트럭 목록 탭 (Tab 1)
  if (foodTruckList.length === 0) {
    return (
      <EmptyList
        iconName="icon-food-cart-mono"
        message="등록된 푸드트럭이 없습니다"
      />
    );
  }
  return (
    <>
      {foodTruckList.map((truck) => (
        <FoodTruckCard key={truck.id} data={truck} />
      ))}
    </>
  );
};

export default DashboardList;